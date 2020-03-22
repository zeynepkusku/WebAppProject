package tr.com.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import tr.com.webapp.entity.Barkod;

@Repository
public class BarkodDAO {

	@Autowired(required = false)
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<Barkod> getBarkodlar() {
		List<Barkod> barkodList = new ArrayList<Barkod>();
		String sql = "SELECT b.id, b.kullanici_id, b.barkod, k.kullanici_adi FROM webapp.barkod b LEFT JOIN webapp.kullanici k ON b.kullanici_id = k.id";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		while (rowSet.next()) {
			Barkod barkod = new Barkod();
			barkod.setId(rowSet.getInt(1));
			barkod.setKullaniciId(rowSet.getInt(2));
			barkod.setBarkod(rowSet.getString(3));
			barkod.setKullaniciAdi(rowSet.getString(4));
			barkodList.add(barkod);
		}
		return barkodList;
	}

	public List<Barkod> getBarkodlarWithFilter(int kullaniciId) {
		List<Barkod> barkodList = new ArrayList<Barkod>();
		String sql = "SELECT b.id, b.kullanici_id, b.barkod, k.kullanici_adi FROM webapp.barkod b LEFT JOIN webapp.kullanici k ON b.kullanici_id = k.id WHERE b.kullanici_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, kullaniciId);
		while (rowSet.next()) {
			Barkod barkod = new Barkod();
			barkod.setId(rowSet.getInt(1));
			barkod.setKullaniciId(rowSet.getInt(2));
			barkod.setBarkod(rowSet.getString(3));
			barkod.setKullaniciAdi(rowSet.getString(4));
			barkodList.add(barkod);
		}
		return barkodList;
	}
}
