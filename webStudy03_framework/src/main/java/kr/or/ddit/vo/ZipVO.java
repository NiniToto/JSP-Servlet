package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(of = { "seq" })
@ToString()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZipVO implements Serializable{

	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String ri;
	private String bldg;
	private String bunji;
	private Integer seq;
	
}
