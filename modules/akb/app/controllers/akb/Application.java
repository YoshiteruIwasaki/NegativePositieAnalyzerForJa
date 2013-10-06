package controllers.akb;

import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Item;
import models.Log;
import play.mvc.Controller;
import play.mvc.Result;
import services.CategoryBeanService;
import services.CategoryService;
import services.ItemBeanService;
import services.ItemService;
import services.LogService;
import beans.CategoryBean;
import beans.ItemBean;

import utils.AkbApplicationConfigUtils;
import views.html.akb.index;

public class Application extends Controller {

	public static Result index() {


		return ok(index.render(AkbApplicationConfigUtils.SITE_FULL_TITLE));
	}

}
