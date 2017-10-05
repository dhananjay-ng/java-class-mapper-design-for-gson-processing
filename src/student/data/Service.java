package student.data;

import java.util.List;

public interface Service {

	public void add(Object obj) throws ServiceException;

	public void update(Object obj) throws ServiceException;

	public void remove(Object obj) throws ServiceException;

	public List<?> list(String filter) throws ServiceException;

	public Object findById(String id) throws ServiceException;

}