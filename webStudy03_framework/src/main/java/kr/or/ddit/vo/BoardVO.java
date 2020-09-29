package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.or.ddit.filter.wrapper.PartWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "bo_no")
public class BoardVO implements Serializable{

	private Integer bo_no;
	@NotBlank
	@Size(max = 120)
	private String bo_title;
	@NotBlank
	@Size(max = 60)
	private String bo_writer;
	@NotBlank
	@Size(max = 7)
	private String bo_date;
	@Size(max = 4000)
	private String bo_content;
	@NotBlank
	@Size(max = 200)
	private String bo_pass;
	@NotBlank
	@Size(max = 200)
	private String bo_ip;
	private Integer bo_hit;
	private Integer bo_parent;
	
	private List<ReplyVO> replyList;
	private List<AttatchVO> attatchList; //db로 부터 받기 위함
	
	private List<PartWrapper> attatchFiles; //클라이언트로부터 받기 위함
	
	public void setAttatchFiles(List<PartWrapper> attatchFiles) {
		this.attatchFiles = attatchFiles;
		if(attatchFiles != null && attatchFiles.size() >0) {
			this.attatchList = new ArrayList<>(attatchFiles.size());
			for(PartWrapper tmp : attatchFiles) {
				attatchList.add(new AttatchVO(tmp));
			}
		}
	}
	
	private int firstAttNo; //첨부 파일을 다중 insert 할 때 시작 PK
}
