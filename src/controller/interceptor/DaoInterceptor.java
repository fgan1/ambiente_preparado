package controller.interceptor;

import javax.persistence.PersistenceException;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import dao.jpa.core.DaoFactory;

@RequestScoped
@Intercepts
public class DaoInterceptor implements Interceptor {

	private DaoFactory daoFactory;

	public DaoInterceptor(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public boolean accepts(ResourceMethod method) {
		return true;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {
		try {
			System.out.println("Inicio Interceptador");

			this.daoFactory.beginTransaction();

			stack.next(method, instance);

			this.daoFactory.commitTransaction();
			this.daoFactory.close();

			System.out.println("Fim Interceptador");
		} catch (PersistenceException e) {
			// TODO
		} catch (Exception e) {
			// TODO
		}
	}
}