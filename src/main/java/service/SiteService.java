package service;

import models.SiteModel;
import java.util.*;
public interface SiteService {
    List<SiteModel> getAllSite();
    Map<String,Object> getSiteByName(String name);
    SiteModel getSiteByName(String name, boolean isContained);
}
