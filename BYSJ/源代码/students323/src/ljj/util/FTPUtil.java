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
	 * 获取FTPClient对象
	 * @param ftpHost FTP主机服务器
	 * @param ftpPassword FTP 登录密码
	 * @param ftpUserName FTP登录用户名
	 * @param ftpPort FTP端口 默认为21
	 * @return
	 */
	
	//关闭FTP客户端
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
	
	//连接客户端
	public static FTPClient getFTPClient(String ftpHost,int ftpPort,
			String ftpUserName, String ftpPassword) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.info("未连接到FTP,用户名或密码错误!");
				ftpClient.disconnect();
                ftpClient.abort();

			} else {
				logger.info("FTP连接成功!");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			logger.info("FTP的IP地址可能错误，请正确配置。");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("FTP的端口错误,请正确配置。");
		}
		return ftpClient;
	}
	
	
	/**
     * 上传本地文件 或 目录 至 FTP 服务器----保持 FTP 服务器与本地 文件目录结构一致
     *
     * @param ftpClient  连接成功有效的 FTPClinet
     * @param uploadFile 待上传的文件 或 文件夹(此时会遍历逐个上传)
     * @throws Exception
     */
    public static void uploadFiles(FTPClient ftpClient, File uploadFile) {
        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println(">>>>>FTP服务器连接已经关闭或者连接无效*****放弃文件上传****");
            return;
        }
        if (uploadFile == null || !uploadFile.exists()) {
            System.out.println(">>>>>待上传文件为空或者文件不存在*****放弃文件上传****");
            return;
        }
        try {
            if (uploadFile.isDirectory()) {
                /**如果被上传的是目录时
                 * makeDirectory：在 FTP 上创建目录(方法执行完，服务器就会创建好目录，如果目录本身已经存在，则不会再创建)
                 * 1）可以是相对路径，即不以"/"开头，相对的是 FTPClient 当前的工作路径，如 "video"、"视频" 等，会在当前工作目录进行新建目录
                 * 2）可以是绝对路径，即以"/"开头，与 FTPCLient 当前工作目录无关，如 "/images"、"/images/2018"
                 * 3）注意多级目录时，必须确保父目录存在，否则创建失败，
                 *      如 "video/201808"、"/images/2018" ，如果 父目录 video与images不存在，则创建失败
                 * */
                ftpClient.makeDirectory(uploadFile.getName());
                /**变更 FTPClient 工作目录到新目录
                 * 1)不以"/"开头表示相对路径，新目录以当前工作目录为基准，即当前工作目录下不存在此新目录时，变更失败
                 * 2)参数必须是目录，当是文件时改变路径无效*/
                ftpClient.changeWorkingDirectory(uploadFile.getName());
 
                File[] listFiles = uploadFile.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    File loopFile = listFiles[i];
                    if (loopFile.isDirectory()) {
                        /**如果有子目录，则迭代调用方法进行上传*/
                        uploadFiles(ftpClient, loopFile);
                        /**changeToParentDirectory：将 FTPClient 工作目录移到上一层
                         * 这一步细节很关键，子目录上传完成后，必须将工作目录返回上一层，否则容易导致文件上传后，目录不一致
                         * */
                        ftpClient.changeToParentDirectory();
                    } else {
                        /**如果目录中全是文件，则直接上传*/
                        FileInputStream input = new FileInputStream(loopFile);
                        ftpClient.storeFile(loopFile.getName(), input);
                        input.close();
                        System.out.println(">>>>>文件上传成功****" + loopFile.getPath());
                    }
                }
            } else {
                /**如果被上传的是文件时*/
                FileInputStream input = new FileInputStream(uploadFile);
                /** storeFile:将本地文件上传到服务器
                 * 1）如果服务器已经存在此文件，则不会重新覆盖,即不会再重新上传
                 * 2）如果当前连接FTP服务器的用户没有写入的权限，则不会上传成功，但是也不会报错抛异常
                 * */
                ftpClient.storeFile(uploadFile.getName(), input);
                input.close();
                System.out.println(">>>>>文件上传成功****" + uploadFile.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
 
    //创建目录
    public static void createDir(FTPClient ftpClient,String Dirname,File uploadFile) throws IOException {
        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println(">>>>>FTP服务器连接已经关闭或者连接无效*****放弃文件上传****");
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
             
                    /**如果被上传的是文件时*/
                /** storeFile:将本地文件上传到服务器
                 * 1）如果服务器已经存在此文件，则不会重新覆盖,即不会再重新上传
                 * 2）如果当前连接FTP服务器的用户没有写入的权限，则不会上传成功，但是也不会报错抛异常
                 * */
                    FileInputStream input;
					input = new FileInputStream(uploadFile);
					ftpClient.storeFile(uploadFile.getName(), input);
                    input.close();
                    System.out.println(">>>>>文件上传成功****" + uploadFile.getPath());
                
    }
    

 
}
