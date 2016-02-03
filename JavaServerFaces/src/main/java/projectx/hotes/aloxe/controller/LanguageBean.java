package projectx.hotes.aloxe.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="language")
@Component(value = "language")
@Scope("session")

public class LanguageBean implements Serializable{

	public LanguageBean() {
		localeCode= "fr";
		locale = new Locale(localeCode);
	}


	private static final long serialVersionUID = 1L;

	private String localeCode;
	private Locale locale;

	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("Fran\u00E7ais", Locale.FRENCH.toString());
		countries.put("English", Locale.ENGLISH.toString()); 
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e){

		String newLocaleValue = e.getNewValue().toString();

		//loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if(entry.getValue().toString().equals(newLocaleValue)){

				Locale tmp = new Locale((String)entry.getValue());
				FacesContext.getCurrentInstance()
				.getViewRoot().setLocale(tmp);
				locale = tmp;

			}
		}
	}

	//value change event listener
	public void changeLocale(String eventLocale){

		//loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {
			if(entry.getValue().toString().equals(eventLocale)){
				Locale tmp = new Locale((String)entry.getValue());
				FacesContext.getCurrentInstance().getViewRoot().setLocale(tmp);
				locale = tmp;
				localeCode = eventLocale;
			}
		}
	}

	public Locale getLocale() {
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}