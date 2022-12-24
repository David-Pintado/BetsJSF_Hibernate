package eredua.bean;



import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;

public class LoginBean {

	private String izena;
	private String pasahitza;
	private BLFacade facadeBL;

	private boolean disable1;
	private boolean disable2;
	private boolean disable3;
	private boolean disable4;
	private boolean disable5;
	private boolean render3;


	public LoginBean() {
		this.setFacadeBL(FacadeBean.getBusinessLogic());
	}


	public String getIzena() {
		return izena;
	}


	public void setIzena(String izena) {
		this.izena = izena;
	}


	public String getPasahitza() {
		return pasahitza;
	}


	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public BLFacade getFacadeBL() {
		return facadeBL;
	}


	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}

	public boolean getDisable1(){
		return disable1;
	}
	public void setDisable1(boolean disable1){
		this.disable1 = disable1;
	}

	public boolean getDisable2(){
		return disable2;
	}
	public void setDisable2(boolean disable2){
		this.disable2 = disable2;
	}

	public boolean isDisable3() {
		return disable3;
	}

	public void setDisable3(boolean disable3) {
		this.disable3 = disable3;
	}

	public boolean getRender3(){
		return render3;
	}
	public void setRender3(boolean render3){
		this.render3 = render3;
	}

	public boolean isDisable4() {
		return disable4;
	}


	public void setDisable4(boolean disable4) {
		this.disable4 = disable4;
	}
	
	


	public boolean isDisable5() {
		return disable5;
	}


	public void setDisable5(boolean disable5) {
		this.disable5 = disable5;
	}


	public String adminDa(){
		if(this.facadeBL.existitzenDa(this.izena, this.pasahitza)==true) {
			if(this.izena.equals("admin")==true && this.pasahitza.equals("pass")==true){
				disable1=true;
				disable2=true;
				disable3=true;
				disable4=true;
				disable5=true;
				render3=true;
			}else{
				disable1=true;
				disable2=false;
				disable3=false;
				disable4=true;
				disable5=false;
				render3=true;
			}
		}else{
				FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ez da existitzen erabiltzaile hori"));
				return "stay";
		}
		System.out.println("Aukerak");
		return "aukerak";

	}
}

