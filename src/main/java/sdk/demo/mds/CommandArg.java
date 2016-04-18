package sdk.demo.mds;

import com.beust.jcommander.Parameter;

public class CommandArg {
	public CommandArg() {

	}

	@Parameter(names = { "-s", "-srvId" }, description = "iPaaS service Id(String)")
	private String srvId = "MDS001";
	@Parameter(names = { "-a", "-authAddr" }, description = "auth address(String)")
	private String authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	@Parameter(names = { "-u", "-authUser" }, description = "auth user(String)")
	private String authUser = "981939211@qq.com";
	@Parameter(names = { "-p", "-authPasswd" }, description = "auth password(String)")
	private String authPasswd = "1234567";
	@Parameter(names = { "-t", "-topic" }, description = "message topic(String)")
	private String topic = "82AF9FF99F164CB196D06C41EF1A2884_MDS001_723879339";
	@Parameter(names = { "-c", "-concurrent" }, description = "thread nums(Integer)")
	private int threadNum = 1;
	@Parameter(names = { "-r", "-runs" }, description = "loop nums(Integer)")
	private int times = 1;
	public String getSrvId() {
		return srvId;
	}
	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}
	public String getAuthAddr() {
		return authAddr;
	}
	public void setAuthAddr(String authAddr) {
		this.authAddr = authAddr;
	}
	public String getAuthUser() {
		return authUser;
	}
	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}
	public String getAuthPasswd() {
		return authPasswd;
	}
	public void setAuthPasswd(String authPasswd) {
		this.authPasswd = authPasswd;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	
}
