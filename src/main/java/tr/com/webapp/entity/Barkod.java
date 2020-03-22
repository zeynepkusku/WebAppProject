package tr.com.webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "barkod")
@EntityListeners(AuditingEntityListener.class)
public class Barkod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "kullanici_id")
	private int kullaniciId;

	@Column(name = "barkod")
	private String barkod;
	
	private String kullaniciAdi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}
}
