package com.pras.dao;

import java.util.List;

import com.pras.dto.RaoDto;
import com.pras.model.Rao;

public interface RaoDao {

	public void addRao(RaoDto rao);
	public void removeRao(long id);
	public void editRao(long id, RaoDto rao);
	public List<RaoDto> getAll();
	public RaoDto getRao(long id);
	
}