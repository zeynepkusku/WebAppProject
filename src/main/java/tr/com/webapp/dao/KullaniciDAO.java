package tr.com.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import tr.com.webapp.entity.Kullanici;

@Repository
public class KullaniciDAO {

	@Autowired(required = false)
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public void save(final Kullanici kullanici) {
		String sql = "INSERT INTO webapp.kullanici(kullanici_adi, parola) VALUES (?, ?)";
		
		jdbcTemplate.update(sql, kullanici.getKullaniciAdi(), kullanici.getParola());
	}

	public List<Kullanici> getKullanicilar() {
		List<Kullanici> kullaniciList = new ArrayList<Kullanici>();
		String sql = "SELECT * FROM webapp.kullanici";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		while (rowSet.next()) {
			Kullanici kullanici = new Kullanici();
			kullanici.setId(rowSet.getInt(1));
			kullanici.setKullaniciAdi(rowSet.getString(2));
			kullanici.setParola(rowSet.getString(3));
			kullaniciList.add(kullanici);
		}
		return kullaniciList;
	}

	public void delete(Kullanici kullanici) {
		String sql = "DELETE FROM webapp.kullanici WHERE id = ?";
		
		jdbcTemplate.update(sql, kullanici.getId());
	}

	public Kullanici find(Kullanici kullaniciVar) {
		Kullanici kullanici = new Kullanici();
		String sql = "SELECT * FROM webapp.kullanici WHERE kullanici_adi = ? AND parola = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, kullaniciVar.getKullaniciAdi(), kullaniciVar.getParola());
		while (rowSet.next()) {
			kullanici.setId(rowSet.getInt(1));
			kullanici.setKullaniciAdi(rowSet.getString(2));
			kullanici.setParola(rowSet.getString(3));
		}
		return kullanici;
	}

	public void update(Kullanici kullanici) {
		String sql = "UPDATE kullanici SET kullanici_adi = ?, parola = ? WHERE id= ?";
		
		jdbcTemplate.update(sql,kullanici.getKullaniciAdi(),kullanici.getParola(), kullanici.getId());
		
	}
	
}
