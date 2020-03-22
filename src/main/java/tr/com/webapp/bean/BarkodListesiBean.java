package tr.com.webapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import tr.com.webapp.entity.Barkod;
import tr.com.webapp.entity.Kullanici;
import tr.com.webapp.service.BarkodService;
import tr.com.webapp.service.KullaniciService;

@ManagedBean(name = "barkodListesiBean")
@Named
@ViewScoped
public class BarkodListesiBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Barkod> barkodlar;

	@Autowired
	private BarkodService barkodService;
	
	private int kullaniciId;
	private List<Kullanici> kullanicilar;

	@Autowired
	private KullaniciService kullaniciService;

	@PostConstruct
	public void init() {
		kullanicilar = new ArrayList<Kullanici>();
		loadKullanicilar();
		barkodlar = new ArrayList<Barkod>();
		loadBarkodlar();
		System.out.println();
	}

	private void loadKullanicilar() {
		kullanicilar = kullaniciService.getKullanicilar();
	}

	private void loadBarkodlar() {
		barkodlar = barkodService.getBarkodlar();
	}

	public void filterBarkodlar() {
		if (kullaniciId == 0) {
			loadBarkodlar();
			return;
		}
		barkodlar = barkodService.getBarkodlarWithFilter(kullaniciId);
	}
	
	public void onload() {
		kullaniciId = 0;
		loadKullanicilar();
		loadBarkodlar();
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

	public List<Barkod> getBarkodlar() {
		return barkodlar;
	}

	public void setBarkodlar(List<Barkod> barkodlar) {
		this.barkodlar = barkodlar;
	}

	public int getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public List<Kullanici> getKullanicilar() {
		return kullanicilar;
	}

	public void setKullanicilar(List<Kullanici> kullanicilar) {
		this.kullanicilar = kullanicilar;
	}

}
