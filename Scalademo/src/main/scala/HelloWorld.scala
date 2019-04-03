package org.wq.scala.ml

import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.{SparkConf, SparkContext}
import java.sql.{Connection, DriverManager, PreparedStatement}
/**
  * Created by Administrator on 2016/10/24.
  */
object FP_GrowthTest {


  def main(args:Array[String]){
    val conf = new SparkConf().setAppName("FPGrowthTest").setMaster("local")
    val sc = new SparkContext(conf)
    //设置参数
    //最小支持度
    val minSupport=0.2
    //最小置信度
    val minConfidence=0.6
    //数据分区
    val numPartitions=4

    //取出数据
    val data = sc.textFile("D://Dwork/Idea_workspace/Scalademo/src/main/scala/FPGrothDemo.txt")
    println(data)
    //把数据通过空格分割
    val transactions=data.map(x=>x.split(" "))

    transactions.cache()
    //创建一个FPGrowth的算法实列
    val fpg = new FPGrowth()
    //设置训练时候的最小支持度和数据分区
    fpg.setMinSupport(minSupport)
    fpg.setNumPartitions(numPartitions)

    //把数据带入算法中
    val model = fpg.run(transactions)

    //查看所有的频繁项集，并且列出它出现的次数
    model.freqItemsets.collect().foreach(itemset=>{
      println( itemset.items.mkString("[", ",", "]")+","+itemset.freq)
    })

    //通过置信度筛选出推荐规则则
    //antecedent表示前项
    //consequent表示后项
    //confidence表示规则的置信度
    //这里可以把规则写入到Mysql数据库中，以后使用来做推荐
    //如果规则过多就把规则写入redis，这里就可以直接从内存中读取了，我选择的方式是写入Mysql，然后再把推荐清单写入redis
    model.generateAssociationRules(minConfidence).collect().foreach(rule=>{
      println(rule.antecedent.mkString(",")+"-->"+
        rule.consequent.mkString(",")+"-->"+ rule.confidence)
    })


    //查看规则生成的数量
    println(model.generateAssociationRules(minConfidence).collect().length)

    //并且所有的规则产生的推荐，后项只有1个，相同的前项产生不同的推荐结果是不同的行
    //不同的规则可能会产生同一个推荐结果，所以样本数据过规则的时候需要去重

    //将数据插入数据库部分
    var url="jdbc:mysql://localhost:3306/bigdata?useUnicode=true&characterEncoding=utf-8&useSSL=false"
    //驱动名称
    var driver="com.mysql.jdbc.Driver"
    //用户名
    var username="root"
    //密码
    var password="123456"
    //初始化连接
    var conn: Connection = null
    var ps:PreparedStatement=null
    val deleteitemsql="delete from freqitemsets"
    val itemsql="insert into freqitemsets(items,itemsfreq)  values(?,?)"
    val deleterulesql="delete from associationrules"
    val rulesql="insert into associationrules(antecedent,consequent,confidence) values(?,?,?)"
      try {
        //        注册Driver
        Class.forName(driver)
        //得到连接
        conn = DriverManager.getConnection(url, username, password)

        //      执行删除原来的associationrules表的操作
        ps = conn.prepareStatement(deleteitemsql)
        ps.executeUpdate()


        //      执行插入操作
        model.freqItemsets.collect().foreach(itemset => {
          //println( itemset.items.mkString("[", ",", "]")+","+itemset.freq)
          ps = conn.prepareStatement(itemsql)
          ps.setString(1, itemset.items.mkString(","))
          ps.setInt(2, (itemset.freq).toInt)
          ps.executeUpdate()
        })


        //    执行删除原来的freqitemsets表的操作
        ps = conn.prepareStatement(deleterulesql)
        ps.executeUpdate()



        //    执行插入操作
        model.generateAssociationRules(minConfidence).collect().foreach(rule => {
          //          println(rule.antecedent.mkString(",")+"-->"+
          //            rule.consequent.mkString(",")+"-->"+ rule.confidence)
          ps = conn.prepareStatement(rulesql)
          ps.setString(1, rule.antecedent.mkString(","))
          ps.setString(2, rule.consequent.mkString(","))
          ps.setDouble(3, rule.confidence)
          ps.executeUpdate()
        })

      }catch{
        case e: Exception => println("Mysql Exception")
      }finally {
        if (ps != null) {
          ps.close()
        }
        if (conn != null) {
          conn.close()
        }
      }

  }
}
