package com.imooc.compareto;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class FlowBeanDriver {

	public static void main(String[] args) throws Exception {
		// 输入路径（处理E:\temp\input下的***文件）
		String inputPath = "E:\\temp\\input";
		// 输出路径（output文件夹不能存在，否则报错）
		String outputPath = "E:\\temp\\output";

		Configuration conf = new Configuration();

		// 1 获取Job对象
		Job job = Job.getInstance(conf);

		// 2 设置jar存储位置(当前类.class)
		job.setJarByClass(FlowBeanDriver.class);

		// 3 关联Map和Reduce类
		job.setMapperClass(FlowBeanMapper.class);
		job.setReducerClass(FlowBeanReducer.class);

		// 4 设置Mapper阶段输出数据的key和value类型
		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);

		// 5 设置最终数据输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		// 6 设置输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		// 7 提交job
		// job.submit();
		job.waitForCompletion(true);

		System.out.println("-------OVER-----------");

	}

}
