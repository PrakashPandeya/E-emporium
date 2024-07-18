package model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;

public class UserModel implements Serializable {
	private static final long serialVersionUID=1L;
	private int userID;
	private LocalDate dob;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String username;
	private String password;
	private String role;
	private String imagePath;
	
	
	
	public UserModel(LocalDate dob, String gender, String email, String phone, String address, String username, String password, Part image) {
		this.dob=dob;
		this.gender=gender;
		this.email=email;
		this.phone=phone;
		this.address=address;
		this.username=username;
		this.password=password;
		this.role="user";
		this.imagePath=getImageUrl(image);
	}
	
	public UserModel() {
		
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private String getImageUrl(Part part) {
	    String savePath = StringUtils.IMAGE_USER_PATH;
	    File fileSaveDir = new File(savePath);
	    String imageUrlFromPart = null;

	    if (!fileSaveDir.exists()) {
	        fileSaveDir.mkdir();
	    }

	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");

	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            break;
	        }
	    }

	    if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
	        imageUrlFromPart = "default_image.jpg";
	    }

	    return imageUrlFromPart;
	}

}