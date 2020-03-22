package tr.com.webapp.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import tr.com.webapp.entity.Kullanici;
import tr.com.webapp.service.KullaniciService;

@ManagedBean(name = "loginBean")
@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Kullanici kullanici;
	
	@Autowired
	private KullaniciService kullaniciService;

	@PostConstruct
	public void init() {
		kullanici = new Kullanici();
	}

	public String login() {
		if (kullanici.getKullaniciAdi() == null || kullanici.getKullaniciAdi().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Kullanıcı Adı giriniz!"));
			return "";
		} else if (kullanici.getParola() == null || kullanici.getParola().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Parola giriniz!"));
			return "";
		}
		kullanici = kullaniciService.find(kullanici);
		if (kullanici.getId() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Kullanıcı Adı veya Parola hatalı!"));
			return "";
		}
		return "kullanici.xhtml?faces-redirect=true";
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

}
