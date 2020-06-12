package ljj.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.Properties;
 
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
 
public class FTPUtil {
	
	private static Logger logger = Logger.getLogger(FTPUtil.class);
	
	/**
	 * ��ȡFTPClient����
	 * @param ftpHost FTP����������
	 * @param ftpPassword FTP ��¼����
	 * @param ftpUserName FTP��¼�û���
	 * @param ftpPort FTP�˿� Ĭ��Ϊ21
	 * @return
	 */
	
	//�ر�FTP�ͻ���
	public static FTPClient closeFTPConnect(FTPClient ftpClient) {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.abort();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }
	
	//���ӿͻ���
	public static FTPClient getFTPClient(String ftpHost,int ftpPort,
			String ftpUserName, String ftpPassword) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// ����FTP������
			ftpClient.login(ftpUserName, ftpPassword);// ��½FTP������
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.info("δ���ӵ�FTP,�û������������!");
				ftpClient.disconnect();
                ftpClient.abort();

			} else {
				logger.info("FTP���ӳɹ�!");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			logger.info("FTP��IP��ַ���ܴ�������ȷ���á�");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("FTP�Ķ˿ڴ���,����ȷ���á�");
		}
		return ftpClient;
	}
	
	
	/**
     * �ϴ������ļ� �� Ŀ¼ �� FTP ������----���� FTP �������뱾�� �ļ�Ŀ¼�ṹһ��
     *
     * @param ftpClient  ���ӳɹ���Ч�� FTPClinet
     * @param uploadFile ���ϴ����ļ� �� �ļ���(��ʱ���������ϴ�)
     * @throws Exception
     */
    public static void uploadFiles(FTPClient ftpClient, File uploadFile) {
        /**��� FTP �����Ѿ��رգ�����������Ч����ֱ�ӷ���*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println(">>>>>FTP�����������Ѿ��رջ���������Ч*****�����ļ��ϴ�****");
            return;
        }
        if (uploadFile == null || !uploadFile.exists()) {
            System.out.println(">>>>>���ϴ��ļ�Ϊ�ջ����ļ�������*****�����ļ��ϴ�****");
            return;
        }
        try {
            if (uploadFile.isDirectory()) {
                /**������ϴ�����Ŀ¼ʱ
                 * makeDirectory���� FTP �ϴ���Ŀ¼(����ִ���꣬�������ͻᴴ����Ŀ¼�����Ŀ¼�����Ѿ����ڣ��򲻻��ٴ���)
                 * 1�����������·����������"/"��ͷ����Ե��� FTPClient ��ǰ�Ĺ���·������ "video"��"��Ƶ" �ȣ����ڵ�ǰ����Ŀ¼�����½�Ŀ¼
                 * 2�������Ǿ���·��������"/"��ͷ���� FTPCLient ��ǰ����Ŀ¼�޹أ��� "/images"��"/images/2018"
                 * 3��ע��༶Ŀ¼ʱ������ȷ����Ŀ¼���ڣ����򴴽�ʧ�ܣ�
                 *      �� "video/201808"��"/images/2018" ����� ��Ŀ¼ video��images�����ڣ��򴴽�ʧ��
                 * */
                ftpClient.makeDirectory(uploadFile.getName());
                /**��� FTPClient ����Ŀ¼����Ŀ¼
                 * 1)����"/"��ͷ��ʾ���·������Ŀ¼�Ե�ǰ����Ŀ¼Ϊ��׼������ǰ����Ŀ¼�²����ڴ���Ŀ¼ʱ�����ʧ��
                 * 2)����������Ŀ¼�������ļ�ʱ�ı�·����Ч*/
                ftpClient.changeWorkingDirectory(uploadFile.getName());
 
                File[] listFiles = uploadFile.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    File loopFile = listFiles[i];
                    if (loopFile.isDirectory()) {
                        /**�������Ŀ¼����������÷��������ϴ�*/
                        uploadFiles(ftpClient, loopFile);
                        /**changeToParentDirectory���� FTPClient ����Ŀ¼�Ƶ���һ��
                         * ��һ��ϸ�ںܹؼ�����Ŀ¼�ϴ���ɺ󣬱��뽫����Ŀ¼������һ�㣬�������׵����ļ��ϴ���Ŀ¼��һ��
                         * */
                        ftpClient.changeToParentDirectory();
                    } else {
                        /**���Ŀ¼��ȫ���ļ�����ֱ���ϴ�*/
                        FileInputStream input = new FileInputStream(loopFile);
                        ftpClient.storeFile(loopFile.getName(), input);
                        input.close();
                        System.out.println(">>>>>�ļ��ϴ��ɹ�****" + loopFile.getPath());
                    }
                }
            } else {
                /**������ϴ������ļ�ʱ*/
                FileInputStream input = new FileInputStream(uploadFile);
                /** storeFile:�������ļ��ϴ���������
                 * 1������������Ѿ����ڴ��ļ����򲻻����¸���,�������������ϴ�
                 * 2�������ǰ����FTP���������û�û��д���Ȩ�ޣ��򲻻��ϴ��ɹ�������Ҳ���ᱨ�����쳣
                 * */
                ftpClient.storeFile(uploadFile.getName(), input);
                input.close();
                System.out.println(">>>>>�ļ��ϴ��ɹ�****" + uploadFile.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
 
    //����Ŀ¼
    public static void createDir(FTPClient ftpClient,String Dirname,File uploadFile) throws IOException {
        /**��� FTP �����Ѿ��رգ�����������Ч����ֱ�ӷ���*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println(">>>>>FTP�����������Ѿ��رջ���������Ч*****�����ļ��ϴ�****");
            return;
        }
                try {
					ftpClient.makeDirectory(Dirname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					ftpClient.changeWorkingDirectory(Dirname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             
                    /**������ϴ������ļ�ʱ*/
                /** storeFile:�������ļ��ϴ���������
                 * 1������������Ѿ����ڴ��ļ����򲻻����¸���,�������������ϴ�
                 * 2�������ǰ����FTP���������û�û��д���Ȩ�ޣ��򲻻��ϴ��ɹ�������Ҳ���ᱨ�����쳣
                 * */
                    FileInputStream input;
					input = new FileInputStream(uploadFile);
					ftpClient.storeFile(uploadFile.getName(), input);
                    input.close();
                    System.out.println(">>>>>�ļ��ϴ��ɹ�****" + uploadFile.getPath());
                
    }
    

 
}
