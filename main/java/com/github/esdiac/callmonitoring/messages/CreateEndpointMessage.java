package com.github.esdiac.callmonitoring.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CreateEndpointMessage implements CallMessage,Comparable {
    public String Event = "CreateEndpoint";
    public String callId;
    public String callerId;
    public String Callee;
    public String date;
    public String countryFrom;
    public String countryTo;
    public String callType = "Voice Call";

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    HashMap<String,String> code2country= new HashMap<>();

    {
        code2country.put("+7 840","Abkhazia");
            code2country.put("+93","Afghanistan");
            code2country.put("+355","Albania");
            code2country.put("+213","Algeria");
            code2country.put("+1 684","American Samoa");
            code2country.put("+376","Andorra");
            code2country.put("+244","Angola");
            code2country.put("+1 264","Anguilla");
            code2country.put("+1 268","Antigua and Barbuda");
            code2country.put("+54","Argentina");
            code2country.put("+374","Armenia");
            code2country.put("+297","Aruba");
            code2country.put("+247","Ascension");
            code2country.put("+61","Australia");
            code2country.put("+672","Australian External Territories");
            code2country.put("+43","Austria");
            code2country.put("+994","Azerbaijan");
            code2country.put("+1 242","Bahamas");
            code2country.put("+973","Bahrain");
            code2country.put("+880","Bangladesh");
            code2country.put("+1 246","Barbados");
            code2country.put("+1 268","Barbuda");
            code2country.put("+375","Belarus");
            code2country.put("+32","Belgium");
            code2country.put("+501","Belize");
            code2country.put("+229","Benin");
            code2country.put("+1 441","Bermuda");
            code2country.put("+975","Bhutan");
            code2country.put("+591","Bolivia");
            code2country.put("+387","Bosnia and Herzegovina");
            code2country.put("+267","Botswana");
            code2country.put("+55","Brazil");
            code2country.put("+246","British Indian Ocean Territory");
            code2country.put("+1 284","British Virgin Islands");
            code2country.put("+673","Brunei");
            code2country.put("+359","Bulgaria");
            code2country.put("+226","Burkina Faso");
            code2country.put("+257","Burundi");
            code2country.put("+855","Cambodia");
            code2country.put("+237","Cameroon");
            code2country.put("+1","Canada");
            code2country.put("+238","Cape Verde");
            code2country.put("+ 345","Cayman Islands");
            code2country.put("+236","Central African Republic");
            code2country.put("+235","Chad");
            code2country.put("+56","Chile");
            code2country.put("+86","China");
            code2country.put("+61","Christmas Island");
            code2country.put("+61","Cocos-Keeling Islands");
            code2country.put("+57","Colombia");
            code2country.put("+269","Comoros");
            code2country.put("+242","Congo");
            code2country.put("+243","Congo, Dem. Rep. of (Zaire)");
            code2country.put("+682","Cook Islands");
            code2country.put("+506","Costa Rica");
            code2country.put("+385","Croatia");
            code2country.put("+53","Cuba");
            code2country.put("+599","Curacao");
            code2country.put("+537","Cyprus");
            code2country.put("+420","Czech Republic");
            code2country.put("+45","Denmark");
            code2country.put("+246","Diego Garcia");
            code2country.put("+253","Djibouti");
            code2country.put("+1 767","Dominica");
            code2country.put("+1 809","Dominican Republic");
            code2country.put("+670","East Timor");
            code2country.put("+56","Easter Island");
            code2country.put("+593","Ecuador");
            code2country.put("+20","Egypt");
            code2country.put("+503","El Salvador");
            code2country.put("+240","Equatorial Guinea");
            code2country.put("+291","Eritrea");
            code2country.put("+372","Estonia");
            code2country.put("+251","Ethiopia");
            code2country.put("+500","Falkland Islands");
            code2country.put("+298","Faroe Islands");
            code2country.put("+679","Fiji");
            code2country.put("+358","Finland");
            code2country.put("+33","France");
            code2country.put("+596","French Antilles");
            code2country.put("+594","French Guiana");
            code2country.put("+689","French Polynesia");
            code2country.put("+241","Gabon");
            code2country.put("+220","Gambia");
            code2country.put("+995","Georgia");
            code2country.put("+49","Germany");
            code2country.put("+233","Ghana");
            code2country.put("+350","Gibraltar");
            code2country.put("+30","Greece");
            code2country.put("+299","Greenland");
            code2country.put("+1 473","Grenada");
            code2country.put("+590","Guadeloupe");
            code2country.put("+1 671","Guam");
            code2country.put("+502","Guatemala");
            code2country.put("+224","Guinea");
            code2country.put("+245","Guinea-Bissau");
            code2country.put("+595","Guyana");
            code2country.put("+509","Haiti");
            code2country.put("+504","Honduras");
            code2country.put("+852","Hong Kong SAR China");
            code2country.put("+36","Hungary");
            code2country.put("+354","Iceland");
            code2country.put("+91","India");
            code2country.put("+62","Indonesia");
            code2country.put("+98","Iran");
            code2country.put("+964","Iraq");
            code2country.put("+353","Ireland");
            code2country.put("+972","Israel");
            code2country.put("+39","Italy");
            code2country.put("+225","Ivory Coast");
            code2country.put("+1 876","Jamaica");
            code2country.put("+81","Japan");
            code2country.put("+962","Jordan");
            code2country.put("+7 7","Kazakhstan");
            code2country.put("+254","Kenya");
            code2country.put("+686","Kiribati");
            code2country.put("+965","Kuwait");
            code2country.put("+996","Kyrgyzstan");
            code2country.put("+856","Laos");
            code2country.put("+371","Latvia");
            code2country.put("+961","Lebanon");
            code2country.put("+266","Lesotho");
            code2country.put("+231","Liberia");
            code2country.put("+218","Libya");
            code2country.put("+423","Liechtenstein");
            code2country.put("+370","Lithuania");
            code2country.put("+352","Luxembourg");
            code2country.put("+853","Macau SAR China");
            code2country.put("+389","Macedonia");
            code2country.put("+261","Madagascar");
            code2country.put("+265","Malawi");
            code2country.put("+60","Malaysia");
            code2country.put("+960","Maldives");
            code2country.put("+223","Mali");
            code2country.put("+356","Malta");
            code2country.put("+692","Marshall Islands");
            code2country.put("+596","Martinique");
            code2country.put("+222","Mauritania");
            code2country.put("+230","Mauritius");
            code2country.put("+262","Mayotte");
            code2country.put("+52","Mexico");
            code2country.put("+691","Micronesia");
            code2country.put("+1 808","Midway Island");
            code2country.put("+373","Moldova");
            code2country.put("+377","Monaco");
            code2country.put("+976","Mongolia");
            code2country.put("+382","Montenegro");
            code2country.put("+1664","Montserrat");
            code2country.put("+212","Morocco");
            code2country.put("+95","Myanmar");
            code2country.put("+264","Namibia");
            code2country.put("+674","Nauru");
            code2country.put("+977","Nepal");
            code2country.put("+31","Netherlands");
            code2country.put("+599","Netherlands Antilles");
            code2country.put("+1 869","Nevis");
            code2country.put("+687","New Caledonia");
            code2country.put("+64","New Zealand");
            code2country.put("+505","Nicaragua");
            code2country.put("+227","Niger");
            code2country.put("+234","Nigeria");
            code2country.put("+683","Niue");
            code2country.put("+672","Norfolk Island");
            code2country.put("+850","North Korea");
            code2country.put("+1 670","Northern Mariana Islands");
            code2country.put("+47","Norway");
            code2country.put("+968","Oman");
            code2country.put("+92","Pakistan");
            code2country.put("+680","Palau");
            code2country.put("+970","Palestinian Territory");
            code2country.put("+507","Panama");
            code2country.put("+675","Papua New Guinea");
            code2country.put("+595","Paraguay");
            code2country.put("+51","Peru");
            code2country.put("+63","Philippines");
            code2country.put("+48","Poland");
            code2country.put("+351","Portugal");
            code2country.put("+1 787","Puerto Rico");
            code2country.put("+974","Qatar");
            code2country.put("+262","Reunion");
            code2country.put("+40","Romania");
            code2country.put("+7","Russia");
            code2country.put("+250","Rwanda");
            code2country.put("+685","Samoa");
            code2country.put("+378","San Marino");
            code2country.put("+966","Saudi Arabia");
            code2country.put("+221","Senegal");
            code2country.put("+381","Serbia");
            code2country.put("+248","Seychelles");
            code2country.put("+232","Sierra Leone");
            code2country.put("+65","Singapore");
            code2country.put("+421","Slovakia");
            code2country.put("+386","Slovenia");
            code2country.put("+677","Solomon Islands");
            code2country.put("+27","South Africa");
            code2country.put("+500","South Georgia and the South Sandwich Islands");
            code2country.put("+82","South Korea");
            code2country.put("+34","Spain");
            code2country.put("+94","Sri Lanka");
            code2country.put("+249","Sudan");
            code2country.put("+597","Suriname");
            code2country.put("+268","Swaziland");
            code2country.put("+46","Sweden");
            code2country.put("+41","Switzerland");
            code2country.put("+963","Syria");
            code2country.put("+886","Taiwan");
            code2country.put("+992","Tajikistan");
            code2country.put("+255","Tanzania");
            code2country.put("+66","Thailand");
            code2country.put("+670","Timor Leste");
            code2country.put("+228","Togo");
            code2country.put("+690","Tokelau");
            code2country.put("+676","Tonga");
            code2country.put("+1 868","Trinidad and Tobago");
            code2country.put("+216","Tunisia");
            code2country.put("+90","Turkey");
            code2country.put("+993","Turkmenistan");
            code2country.put("+1 649","Turks and Caicos Islands");
            code2country.put("+688","Tuvalu");
            code2country.put("+1 340","U.S. Virgin Islands");
            code2country.put("+256","Uganda");
            code2country.put("+380","Ukraine");
            code2country.put("+971","United Arab Emirates");
            code2country.put("+44","United Kingdom");
            code2country.put("+1","United States");
            code2country.put("+598","Uruguay");
            code2country.put("+998","Uzbekistan");
            code2country.put("+678","Vanuatu");
            code2country.put("+58","Venezuela");
            code2country.put("+84","Vietnam");
            code2country.put("+1 808","Wake Island");
            code2country.put("+681","Wallis and Futuna");
            code2country.put("+967","Yemen");
            code2country.put("+260","Zambia");
            code2country.put("+255","Zanzibar");
            code2country.put("+263","Zimbabwe");
    };


    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallerId() {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(callerId, "");
            this.countryFrom = (code2country.get("+"+numberProto.getCountryCode()));
            Phonenumber.PhoneNumber phonenumber = phoneUtil.parse(callerId,countryFrom);
            callerId = phoneUtil.formatOutOfCountryCallingNumber(phonenumber,countryFrom).toString();
        } catch (
                NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
        return callerId;
    }



    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getCallee() {
        return Callee;
    }

    public void setCallee(String Callee) {
        this.Callee = Callee;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(Callee, "");
            this.countryTo = (code2country.get("+"+numberProto.getCountryCode()));
            Phonenumber.PhoneNumber phonenumber = phoneUtil.parse(Callee,countryTo);
            this.Callee = phoneUtil.formatOutOfCountryCallingNumber(phonenumber,countryTo).toString();
        } catch (
                NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        long epoch = Long.parseLong( date );
        Date expiry = new Date( epoch * 1000 );
        this.date = expiry.toString();
    }

    @Override
    public boolean equals(Object o){
        return compareTo(o)==1;
    }
}
