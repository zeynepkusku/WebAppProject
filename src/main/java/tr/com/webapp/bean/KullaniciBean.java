package tr.com.webapp.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import tr.com.webapp.entity.Kullanici;
import tr.com.webapp.service.KullaniciService;

@ManagedBean(name = "kullaniciBean")
@Named
@ViewScoped
public class KullaniciBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private KullaniciService kullaniciService;

	private Kullanici kullanici;

	@PostConstruct
	public void init() {
		kullanici = new Kullanici();
	}

	public void save() {
		if (kullanici.getKullaniciAdi() == null || kullanici.getKullaniciAdi().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Kullanıcı Adı giriniz!"));
			return;
		} else if (kullanici.getParola() == null || kullanici.getParola().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Parola giriniz!"));
			return;
		}
		kullaniciService.save(kullanici);
		kullanici = new Kullanici();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", "Kullanıcı kayıt işlemi başarılı!"));
	}

	public String kullaniciPage() {
		return "kullanici.xhtml?faces-redirect=true";
	}
	
	public String kullaniciListesiPage() {
		return "kullaniciListesi.xhtml?faces-redirect=true";
	}
	
	public String barkodListesiPage() {
		return "barkodListesi.xhtml?faces-redirect=true";
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

}
