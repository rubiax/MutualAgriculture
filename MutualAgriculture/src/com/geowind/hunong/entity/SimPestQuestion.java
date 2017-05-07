package com.geowind.hunong.entity;

import com.geowind.hunong.jpa.Consult;
import com.geowind.hunong.jpa.Pestlib;
import com.geowind.hunong.jpa.Pestquestion;
import com.geowind.hunong.jpa.User;
import com.google.gson.annotations.Expose;

public class SimPestQuestion {

	// Fields
		@Expose
		private Integer qid;
		@Expose
		private String username;
		@Expose
		private String uploadPic;
		@Expose
		private String descr;
		@Expose
		private String utime;
		@Expose
		private String atime;
		@Expose
		private Integer status;
		@Expose
		private String answer;
		
		public Integer getQid() {
			return qid;
		}
		public void setQid(Integer qid) {
			this.qid = qid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUploadPic() {
			return uploadPic;
		}
		public void setUploadPic(String uploadPic) {
			this.uploadPic = uploadPic;
		}
		public String getDescr() {
			return descr;
		}
		public void setDescr(String descr) {
			this.descr = descr;
		}
		public String getUtime() {
			return utime;
		}
		public void setUtime(String utime) {
			this.utime = utime;
		}
		public String getAtime() {
			return atime;
		}
		public void setAtime(String atime) {
			this.atime = atime;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		@Override
		public String toString() {
			return "SimPestQuestion [qid=" + qid + ", username=" + username + ", uploadPic=" + uploadPic + ", descr="
					+ descr + ", utime=" + utime + ", atime=" + atime + ", status=" + status + ", answer=" + answer
					+ "]";
		}
		
		public SimPestQuestion fromPestquestion(Pestquestion questionion) {
			if (questionion == null) {
				return null;
			} else {
				SimPestQuestion simPestQuestion = new SimPestQuestion();
				simPestQuestion.setQid(questionion.getQid());
				simPestQuestion.setUsername(questionion.getUser().getUsername());
				simPestQuestion.setUploadPic(questionion.getUploadPic());
				simPestQuestion.setDescr(questionion.getDescr());
				simPestQuestion.setUtime(questionion.getUtime());
				simPestQuestion.setAnswer(questionion.getAnswer());
				simPestQuestion.setAtime(questionion.getAtime());
				simPestQuestion.setStatus(questionion.getStatus());
				return simPestQuestion;
			}
			
		}
		
}
