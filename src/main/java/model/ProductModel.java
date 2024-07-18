package model;

import java.io.File;

import javax.servlet.http.Part;

import util.StringUtils;

public class ProductModel {
	private int productID;
	private String name;
	private int price;
	private String description;
	private String category;
	private int stock;
	private String imagePath;
	
	public ProductModel(int productID, String name, int price, String description, String category, int stock, Part image) {
		this.productID=productID;
		this.name=name;
		this.price=price;
		this.description=description;
		this.category=category;
		this.stock=stock;
		this.imagePath=getImageUrl(image);
	}
	
	public ProductModel() {

	}

	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	private String getImageUrl(Part part) {
	    String savePath = StringUtils.IMAGE_PRODUCT_PATH;
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
