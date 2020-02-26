package com.apps.kunalfarmah.marsplay;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("numFound")
	private int numFound;

	@SerializedName("start")
	private int start;

	@SerializedName("maxScore")
	private double maxScore;

	@SerializedName("docs")
	private List<DocsItem> docs;


	public void setDocs(List<DocsItem> docs){
		this.docs = docs;
	}

	public List<DocsItem> getDocs(){
		return docs;
	}

	public void setNumFound(int numFound){
		this.numFound = numFound;
	}

	public int getNumFound(){
		return numFound;
	}

	public void setStart(int start){
		this.start = start;
	}

	public int getStart(){
		return start;
	}

	public void setMaxScore(double maxScore){
		this.maxScore = maxScore;
	}

	public double getMaxScore(){
		return maxScore;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"docs = '" + docs + '\'' + 
			",numFound = '" + numFound + '\'' + 
			",start = '" + start + '\'' + 
			",maxScore = '" + maxScore + '\'' + 
			"}";
		}
}