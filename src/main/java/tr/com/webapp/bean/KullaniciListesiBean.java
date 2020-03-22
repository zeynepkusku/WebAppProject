package tr.com.webapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import tr.com.webapp.entity.Kullanici;
import tr.com.webapp.service.KullaniciService;

@ManagedBean(name = "kullaniciListesiBean")
@Named
@ViewScoped
public class KullaniciListesiBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Kullanici> kullanicilar;

	@Autowired
	private KullaniciService kullaniciService;

	@PostConstruct
	public void init() {
		kullanicilar = new ArrayList<Kullanici>();
		loadKullanicilar();
	}

	private void loadKullanicilar() {
		kullanicilar = kullaniciService.getKullanicilar();
	}

	public void delete(Kullanici kullanici) {
		kullaniciService.delete(kullanici);	
		loadKullanicilar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", "Kullanıcı silme işlemi başarılı!"));
	}
	
	public void onload() {
		loadKullanicilar();
	}
	
	public void onRowEdit(RowEditEvent event) {
		Kullanici kullanici = (Kullanici) event.getObject();
		kullaniciService.update(kullanici);	
		loadKullanicilar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", kullanici.getId()+ " ID'li Kullanıcı düzenlendi!"));
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

	public List<Kullanici> getKullanicilar() {
		return kullanicilar;
	}

}
