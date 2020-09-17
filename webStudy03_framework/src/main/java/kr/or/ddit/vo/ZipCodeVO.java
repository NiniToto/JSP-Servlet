package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="zipcode")
@Builder
public class ZipCodeVO implements Serializable{
	@NotBlank
	@Size(max = 7)
	private String zipcode;
	@NotBlank
	@Size(max = 6)
	private String sido;
	@Size(max = 30)
	private String gugun;
	@Size(max = 40)
	private String dong;
	@Size(max = 30)
	private String ri;
	@Size(max = 60)
	private String bldg;
	@Size(max = 30)
	private String bunji;
	@NotBlank
	@Size(max = 22)
	private Integer seq;
	
	public String getAddress() {
		StringBuffer address = new StringBuffer();
		address.append(sido);
		address.append(" " + gugun);
		if(dong!=null) address.append(" " + dong);
		if(ri!=null) address.append(" " + ri);
		if(bldg!=null) address.append(" " + bldg);
		if(bunji!=null) address.append(" " + bldg);
		if(seq!=null) address.append(" " + seq);
		return address.toString();
	}
}










