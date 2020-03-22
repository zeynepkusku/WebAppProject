package tr.com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.webapp.dao.KullaniciDAO;
import tr.com.webapp.entity.Kullanici;

@Service
public class KullaniciService {

	@Autowired
	private KullaniciDAO kullaniciDao;
	
	public void save(Kullanici kullanici) {
		kullaniciDao.save(kullanici);
	}
	
	public List<Kullanici> getKullanicilar() {
		return kullaniciDao.getKullanicilar();
	}

	public void delete(Kullanici kullanici) {
		kullaniciDao.delete(kullanici);
	}

	public Kullanici find(Kullanici kullanici) {
		return kullaniciDao.find(kullanici);
	}

	public void update(Kullanici kullanici) {
		kullaniciDao.update(kullanici);
		
	}


}
