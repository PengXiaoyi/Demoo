package com.imooc.GroupingComparator;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class OrderDriver {

	public static void main(String[] args) throws Exception {
		// 输入路径（处理E:\temp\input下的***文件）
		String inputPath = "E:\\temp\\input";
		// 输出路径（output文件夹不能存在，否则报错）
		String outputPath = "E:\\temp\\output";

		Configuration conf = new Configuration();

		// 1 获取Job对象
		Job job = Job.getInstance(conf);

		// 2 设置jar存储位置(当前类.class)
		job.setJarByClass(OrderDriver.class);

		// 3 关联Map和Reduce类
		job.setMapperClass(OrderMapper.class);
		job.setReducerClass(OrderReducer.class);

		// 4 设置Mapper阶段输出数据的key和value类型
		job.setMapOutputKeyClass(OrderBean.class);
		job.setMapOutputValueClass(NullWritable.class);

		// 5 设置最终数据输出的key和value类型
		job.setOutputKeyClass(OrderBean.class);
		job.setOutputValueClass(NullWritable.class);

		// 6 设置输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		// 8 设置reduce端的分组
		job.setGroupingComparatorClass(OrderGroupingComparator.class);

		// 7 提交job
		// job.submit();
		job.waitForCompletion(true);

		System.out.println("-------OVER-----------");

	}

}
