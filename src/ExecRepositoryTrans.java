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
		LoggerUtil.info(ExecRepositoryTrans.class, "ִ��kettle job start-------------------");

	//	runJob(classesdir + jobName);

		LoggerUtil.info(ExecRepositoryTrans.class, "ִ��kettle job end -------------------");
		}else{

		LoggerUtil.info(ExecRepositoryTrans.class, "������kettle.path.file job ��·���ļ�");
		}*/

		runTransfer();
		}
		/**
		*java ����kettle ת�� 
		*/
		public static void runTransfer(){
		/*Trans trans=null;  
		FileSelector f = null;
		try {
		// ��ʼ��  
		            String fName= "D:\\test.ktr";
		            // ת��Ԫ����  
		        KettleEnvironment.init();//��ʼ��
		        EnvUtil.environmentInit();
		            TransMeta transMeta = new TransMeta(fName);
		            // ת��  
		            trans = new Trans(transMeta);  
		            // ִ��ת��  
		            trans.execute(null);   
		            // �ȴ�ת��ִ�н���  
		            trans.waitUntilFinished();  
		            //�׳��쳣  
		            if(trans.getErrors()>0){  
		                throw new Exception("There are errors during transformation exception!(��������з����쳣)");  
		            }  
		} catch (Exception e) {
		e.printStackTrace();
		}*/
			//java����kettle���ݿ�������Դ���е�ktr
			//��ʼ������
	        try {
				KettleEnvironment.init();
			} catch (KettleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //����DB��Դ��
	        KettleDatabaseRepository repository=new KettleDatabaseRepository();
	        DatabaseMeta databaseMeta=new DatabaseMeta("mysql","mysql","jdbc","localhost","kettle","3306","root","root");
	        //ѡ����Դ��
	        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta=new KettleDatabaseRepositoryMeta("mysql","mysql","Transformation description",databaseMeta);
	        repository.init(kettleDatabaseRepositoryMeta);
	        //������Դ��
	        try {
				repository.connect("admin","admin");
				RepositoryDirectoryInterface directoryInterface=repository.findDirectory("/mysql");
				//ѡ��ת��
				TransMeta transMeta;
				transMeta = repository.loadTransformation("test",directoryInterface,null,true,null);
				Trans trans=new Trans(transMeta);
				trans.execute(null);
				trans.waitUntilFinished();//�ȴ�ֱ�����ݽ���
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
