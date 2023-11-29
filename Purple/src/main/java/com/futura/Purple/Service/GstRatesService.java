package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.GstRates;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.GstRatesRepository;

@Service
public class GstRatesService {
	@Autowired
	private GstRatesRepository gstRatesRepository;

	@Autowired
	private ErrorService errorService;

	public List<GstRates> getAll() {
		return gstRatesRepository.getallActive();
	}

	public GstRates getById(Long id) {
		return gstRatesRepository.getActiveById(id);
	}

	public String add(GstRates gstRates) {
		gstRates.setValidFlag(1);
		gstRatesRepository.save(gstRates);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, GstRates gstRates) {
		GstRates gstRates1 = gstRatesRepository.getActiveById(id);

		if (gstRates.getCompanyId() != null) {
			gstRates1.setCompanyId(gstRates.getCompanyId());
		}
		if (gstRates.getProduct() != null) {
			gstRates1.setProduct(gstRates.getProduct());
		}
		if (gstRates.getPremYear() != null) {
			gstRates1.setPremYear(gstRates.getPremYear());
		}
		if (gstRates.getFromDate() != null) {
			gstRates1.setFromDate(gstRates.getFromDate());
		}
		if (gstRates.getToDate() != null) {
			gstRates1.setToDate(gstRates.getToDate());;
		}
		if (gstRates.getGstRate() != null) {
			gstRates1.setGstRate(gstRates.getGstRate());
		}
		

		gstRatesRepository.save(gstRates1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		GstRates gstRates = gstRatesRepository.getActiveById(id);
		gstRates.setValidFlag(-1);
		gstRatesRepository.save(gstRates);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		gstRatesRepository.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<GstRates> search(String key) {
		return gstRatesRepository.globalSearch(key);
	}
}
