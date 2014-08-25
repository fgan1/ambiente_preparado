package controller.impl;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private Result result;

	public IndexController(Result result) {
		this.result = result;
	}

	@Path("/")
	public void index() {
		result.include("x", "Primeiro Teste !! ");
	}
}
