package student.web;

public class User {
	private String userId;
	private String userName;
	private String password;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		  this.password =password; 
/*
		  byte[] bytesOfPassword; MessageDigest md;
		  
		  try { bytesOfPassword = password.getBytes("UTF-8"); md =
		  MessageDigest.getInstance("MD5"); 
		  byte[] thedigest =md.digest(bytesOfPassword); 
		  BigInteger bigInt = new BigInteger(1, thedigest);
	      String hashtext = bigInt.toString(16); 
			  while (hashtext.length() < 32) {
			  			hashtext = "0" + hashtext;
			  		}	   

		  System.out.println(hashtext);
		  
		  
		  } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		  catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
		*/
	}
	

}
