package tr.com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.webapp.dao.BarkodDAO;
import tr.com.webapp.entity.Barkod;

@Service
public class BarkodService {

	@Autowired
	private BarkodDAO barkodDao;

	public List<Barkod> getBarkodlar() {
		return barkodDao.getBarkodlar();
	}

	public List<Barkod> getBarkodlarWithFilter(int kullaniciId) {
		return barkodDao.getBarkodlarWithFilter(kullaniciId);
	}

}
