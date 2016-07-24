import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;



public class ExecRepositoryTrans {
	public static void main(String[] args) {
		//D:\java\workspace\sics\src\config\kettle
		/*String classesdir = BaseConfiguration.getString("classesdir");
		String jobName = BaseConfiguration.getString("kettle.path.file");
		if(StringUtils.isNullOrEmpty(jobName)){
		LoggerUtil.info(ExecRepositoryTrans.class, "执行kettle job start-------------------");

	//	runJob(classesdir + jobName);

		LoggerUtil.info(ExecRepositoryTrans.class, "执行kettle job end -------------------");
		}else{

		LoggerUtil.info(ExecRepositoryTrans.class, "请配置kettle.path.file job 的路径文件");
		}*/

		runTransfer();
		}
		/**
		*java 调用kettle 转换 
		*/
		public static void runTransfer(){
		/*Trans trans=null;  
		FileSelector f = null;
		try {
		// 初始化  
		            String fName= "D:\\test.ktr";
		            // 转换元对象  
		        KettleEnvironment.init();//初始化
		        EnvUtil.environmentInit();
		            TransMeta transMeta = new TransMeta(fName);
		            // 转换  
		            trans = new Trans(transMeta);  
		            // 执行转换  
		            trans.execute(null);   
		            // 等待转换执行结束  
		            trans.waitUntilFinished();  
		            //抛出异常  
		            if(trans.getErrors()>0){  
		                throw new Exception("There are errors during transformation exception!(传输过程中发生异常)");  
		            }  
		} catch (Exception e) {
		e.printStackTrace();
		}*/
			//java调用kettle数据库类型资源库中的ktr
			//初始化环境
	        try {
				KettleEnvironment.init();
			} catch (KettleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //创建DB资源库
	        KettleDatabaseRepository repository=new KettleDatabaseRepository();
	        DatabaseMeta databaseMeta=new DatabaseMeta("mysql","mysql","jdbc","localhost","kettle","3306","root","root");
	        //选择资源库
	        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta=new KettleDatabaseRepositoryMeta("mysql","mysql","Transformation description",databaseMeta);
	        repository.init(kettleDatabaseRepositoryMeta);
	        //连接资源库
	        try {
				repository.connect("admin","admin");
				RepositoryDirectoryInterface directoryInterface=repository.findDirectory("/mysql");
				//选择转换
				TransMeta transMeta;
				transMeta = repository.loadTransformation("test",directoryInterface,null,true,null);
				Trans trans=new Trans(transMeta);
				trans.execute(null);
				trans.waitUntilFinished();//等待直到数据结束
				if(trans.getErrors()>0){
					System.out.println("transformation error");
				}else{
					System.out.println("transformation successfully");
				}
			} catch (KettleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
