package com.Generator.apirest.ServiceImpl;


import com.Generator.apirest.entity.SalveProyect;
import com.Generator.apirest.repositorio.SalveProyectRepository;
import com.Generator.apirest.services.SalveProyectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaveProyectServiceImpl implements SalveProyectService {

	protected static final Log logger = LogFactory.getLog(SaveProyectServiceImpl.class);

	@Autowired
	private SalveProyectRepository salveProyectRepository;


	@Override
	public SalveProyect findByProyectoName(String name) {
		SalveProyect proyectoBDA = null;
		Optional<SalveProyect> fileOptional = salveProyectRepository.findByProyectoName(name);
		if (fileOptional.isPresent()) {
			 proyectoBDA = fileOptional.get();
		}
		return proyectoBDA;
	}

	@Override
	public  List<SalveProyect> findByAutor(String autor) {
		 List<SalveProyect> proyectoBDA = null;
		List<Optional<SalveProyect>> fileOptional = salveProyectRepository.findByAutor(autor);
		if (fileOptional.size() > 0) {
			return proyectoBDA = fileOptional.stream().map(salveProyect -> salveProyect.get()).collect(Collectors.toList());
		}
		return proyectoBDA;
	}

	@Override
	public List<SalveProyect> findByUser(String user) {
		List <SalveProyect> proyectoBDA = null;
		List<Optional<SalveProyect>>fileOptional = salveProyectRepository.findByUser(user);
		if (fileOptional.size() > 0) {
			return proyectoBDA = fileOptional.stream().map(salveProyect -> salveProyect.get()).collect(Collectors.toList());
		}
		return proyectoBDA;
	}



	@Override
	public void saveProyectInternamente(SalveProyect proyect) {
		logger.info("Starting saveProyectInternamente");
		 Optional<SalveProyect> fileOptional = salveProyectRepository.findByProyectoName(proyect.getProyectoName());
		if (fileOptional.isPresent()) {
			try {
				logger.info("the proyect be updated");
//				SalveProyect proyectoBDA = salveProyectRepository.findById(fileOptional.get().getId()).get();
				SalveProyect proyectoBDA = fileOptional.get();
				proyectoBDA.setEntidades(proyect.getEntidades());
				proyectoBDA.setAutor(proyect.getAutor());
				proyectoBDA.setProyectoName(proyect.getProyectoName());
				proyectoBDA.setDescription(proyect.getDescription());
				proyectoBDA.setPackageNames(proyect.getPackageNames());
				proyectoBDA.setUser(proyect.getUser());
				salveProyectRepository.save(proyectoBDA);
				logger.info("Save the Proyect");
			} catch (DataAccessException e) { logger.error(" ERROR : " + e); }
		} else {
			logger.info("No hay proyecto y se retorna un new proyecto");
			try {
				salveProyectRepository.save(proyect);
				logger.info("Save the Proyect");
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
	}


	@Override
	public boolean saveProyect(SalveProyect proyect){
		logger.info("Starting saveProyect");
		boolean resultado = false;
		Optional<SalveProyect> fileOptional = salveProyectRepository.findByProyectoName(proyect.getProyectoName());
		if (fileOptional.isPresent()) {
			try {
				logger.info("the proyect be updated");
				SalveProyect proyectoBDA = fileOptional.get();
				proyectoBDA.setEntidades(proyect.getEntidades());
				proyectoBDA.setAutor(proyect.getAutor());
				proyectoBDA.setProyectoName(proyect.getProyectoName());
				proyectoBDA.setDescription(proyect.getDescription());
				proyectoBDA.setPackageNames(proyect.getPackageNames());
				proyectoBDA.setUser(proyect.getUser());
				salveProyectRepository.save(proyectoBDA);
				logger.info("Save the Proyect");
				resultado = true;
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		} else {
			logger.info("No hay proyecto y se retorna un new proyecto");
			try {
				salveProyectRepository.save(proyect);
				logger.info("Save the Proyect");
				resultado = true;
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return resultado;
	}




	@Override
	public SalveProyect findById(Long id) {
		logger.info("ejecute  findById()");
		Optional<SalveProyect> fileOptional = salveProyectRepository.findById(id);
		if (fileOptional.isPresent()) {
			SalveProyect proyectoBDA = fileOptional.get();
			return proyectoBDA;
		}else {
			return new SalveProyect();
		}
	}

	@Override
	public Long countProyect(String name) {
		Long count = salveProyectRepository.CountSalveProyect(name);
		if(count != null) {
			return count;
		}
		return 0l; 
	}	
		
		
}



//	@Override
//	public boolean saveProyect(SalveProyect proyect) {
//		logger.info("Starting saveProyect()");
//		try {
//			salveProyectRepository.save(proyect);
//			return true;
//		} catch (DataAccessException e) {
//			logger.error(" ERROR : " + e);
//			return false;
//		}
//	}

















