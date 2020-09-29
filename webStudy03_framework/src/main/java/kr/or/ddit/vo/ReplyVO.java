package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"rep_no", "bo_no"})
public class ReplyVO implements Serializable{

	private Integer rep_no;
	private Integer bo_no;
	@Size(max = 800)
	private String rep_content;
	@NotBlank
	@Size(max = 15)
	private String rep_writer;
	@NotBlank
	private String rep_date;
	@NotBlank
	@Size(max = 50)
	private String rep_ip;
	@NotBlank
	@Size(max = 200)
	private String rep_pass;
	
}
