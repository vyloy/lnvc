/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import com.lorent.lvmc.bean.OptionDto;
import com.lorent.lvmc.bean.VoteDataChildDto;
import com.lorent.lvmc.bean.VoteDataDto;
import com.lorent.lvmc.bean.VoteItemResultBean;
import com.lorent.lvmc.bean.VoteItemResultOptionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 *
 * @author chard
 */
public class XmlUtilParse {
    private Map<String,Object> map=new HashMap<String, Object>();
    private Map<String,Object> pathmap=null;
    private List<Map> Themelist=new ArrayList<Map> ();
    private Logger log = Logger.getLogger(XmlUtilParse.class);
    public List<Map> getThemelist() {
        return Themelist;
    }
    
    /**
     * 
     * @param xmlString是xml格式的字符串 
     */
    public XmlUtilParse(String xmlString){
        treeWalk(xmlString);
    }
    public XmlUtilParse(Document document){
        treeWalk(document);
    }
    
    public Object getElementValue(String ElementName){
        return map.get(ElementName);
    }
    
    public  void treeWalk(Document document) {
        try{
            treeWalk(document.getRootElement());
            if (null != optionDtosList && optionDtosList.size() > 0 && null != voteDataChildDto) {
                voteDataChildDto.getList().add(optionDtosList.toArray(new OptionDto[optionDtosList.size()]));
            }
        } catch(Exception e){
        	log.error("treeWalk", e);
        	e.printStackTrace();   
        }
    }
    
    private  void treeWalk(String xmlString) {
        try {
            Document doc = DocumentHelper.parseText(xmlString);
            treeWalk(doc.getRootElement());
            this.elementpath="";
        } catch (DocumentException ex) {
            log.error("treeWalk", ex);
        }
    }
   private String elementpath="";
    /**
     * 遍历节点，按照element name value 键值对暂存于map中
     * @param element 
     */
    private List<VoteItemResultBean> itemResultBeansList=new ArrayList<VoteItemResultBean>();

    public List<VoteItemResultBean> getItemResultBeansList() {
        return itemResultBeansList;
    }
    
    private VoteItemResultBean itemResultBean=null;
    private VoteItemResultOptionBean itemResultOptionBean =null;
    private List<VoteDataDto> voteDataDtosList=new ArrayList<VoteDataDto>();
    private VoteDataDto voteDataDto=null;
    private VoteDataChildDto voteDataChildDto=null;
    private OptionDto optionDto=null;
    private List<OptionDto> optionDtosList=null;
    private Map<String,String> votedOptionRecordMap=new HashMap<String, String>();

    public Map<String, String> getVotedOptionRecordMap() {
        return votedOptionRecordMap;
    }
    
    public List<VoteDataDto> getVoteDataDtosList() {
        return voteDataDtosList;
    }
    
    private void treeWalk(Element element) {
        List liste = element.attributes();
        for (int j = 0; j < liste.size(); j++) {
            Attribute item = (Attribute) liste.get(j);
            if ("name".equals(item.getName())) {
                this.nameValue = item.getValue();
            }
        }
        List list1 = element.elements();
//        System.out.println(list1 + "===================" + list1 != null ? list1.size() : 0);
        int size = list1 != null ? list1.size() : 0;
        if (size < 1) {
            this.namespaceURI = element.getNamespaceURI();
                if (null != element.getName() && element.getName().length() > 0 && null != element.getTextTrim() && element.getTextTrim().length() > 0) {
                    log.debug("element.getPath()" + element.getPath() + " ---- " + element.getTextTrim());
//                   System.out.println("element.getParent()"+element.getParent().getPath());

                    if (map.containsKey(element.getName())) {
                        if (map.get(element.getName()) instanceof String) {
                            List<String> list = new ArrayList<String>();
                            list.add(map.get(element.getName()).toString());
                            list.add(element.getTextTrim());
                            map.remove(element.getName());
                            map.put(element.getName(), list);
                        } else if (map.get(element.getName()) instanceof List) {
                            ((List) map.get(element.getName())).add(element.getTextTrim());
                        }
                    } else {
                        map.put(element.getName(), element.getText());
                    }

                    //投票结果统计
                    //投票表决项id
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='selects']/*[name()='select']/*[name()='id']")) {
//                       if(null!=itemResultBean)
                        itemResultBean = new VoteItemResultBean();
                        itemResultBeansList.add(itemResultBean);
                        itemResultBean.setItemid(element.getTextTrim());
                    }

                    if (itemResultBean != null) {
                        //投票表决项答案ID
                        if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='selects']/*[name()='select']/*[name()='options']/*[name()='option']/*[name()='id']")) {
                            itemResultOptionBean = new VoteItemResultOptionBean();
                            itemResultBean.getOptionSelectedIdList().add(itemResultOptionBean);
                            itemResultOptionBean.setId(element.getTextTrim());
                        }
                        //选项数量
                        if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='selects']/*[name()='select']/*[name()='options']/*[name()='option']/*[name()='num']")) {
                            itemResultOptionBean.setNum(element.getTextTrim());
                        }
                        //选项所占百分比
                        if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='selects']/*[name()='select']/*[name()='options']/*[name()='option']/*[name()='percent']")) {
                            itemResultOptionBean.setPercent(element.getTextTrim());
                        }
                    }


                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='id']")) {
                        voteDataDto = new VoteDataDto();
                        voteDataDtosList.add(voteDataDto);
                        voteDataDto.setThemeid(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='title']")) {
                        voteDataDto.setTheme(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='title_remark']")) {
                        voteDataDto.setThemeDescription(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='begin_time']")) {
                        voteDataDto.setBegin_time(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='effective_time']")) {
                        voteDataDto.setEffective_time(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='creator']")) {
                        voteDataDto.setCreator(element.getTextTrim());
                    }

                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='is_start']")) {
                        voteDataDto.setIsStart(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='is_close']")) {
                        voteDataDto.setIsClose(element.getTextTrim());
                    }

                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='id']")) {
                        if (null != optionDtosList && optionDtosList.size() > 0 && null != voteDataChildDto) {
                            voteDataChildDto.getList().add(optionDtosList.toArray(new OptionDto[optionDtosList.size()]));
                        }
                        optionDtosList = new ArrayList<OptionDto>();
                        voteDataChildDto = new VoteDataChildDto();
                        voteDataDto.getList().add(voteDataChildDto);
                        voteDataChildDto.setTitleid(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='select_title']")) {
                        voteDataChildDto.setTitle(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='select_remark']")) {
                        voteDataChildDto.setTitledescription(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='type']")) {
                        voteDataChildDto.setItemType(element.getTextTrim());
                    }

//                   if (element.getParent().getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='options']/*[name()='option']")) {
//                       optionDtosList=new ArrayList<OptionDto>();
//                       voteDataChildDto.getList().add(optionDtosList.toArray(new OptionDto[optionDtosList.size()]));
//                   }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='options']/*[name()='option']/*[name()='id']")) {
                        optionDto = new OptionDto();
                        optionDtosList.add(optionDto);
                        optionDto.setId(element.getTextTrim());
                    }
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='votes']/*[name()='vote']/*[name()='selects']/*[name()='select']/*[name()='options']/*[name()='option']/*[name()='option_name']")) {
                        optionDto.setName(element.getTextTrim());
                    }

                    //返回用户当前主题下所有已选择选项的id
                    if (element.getPath().equals("/iq/*[name()='query']/*[name()='operate']/*[name()='searchs']/*[name()='search']/*[name()='option_id']")) {
                        if (!votedOptionRecordMap.containsKey(element.getTextTrim())) {
                            votedOptionRecordMap.put(element.getTextTrim(), element.getTextTrim());
                        }
                    }
                }
        } else {
            for(int i=0;i < size; i++){
                Element e = (Element) element.elements().get(i);
                treeWalk(e);
            }
        }
//        int size = element.nodeCount();
//        for (int i = 0; i < size; i++) {
//            Node node = element.node(i);
//            if (node instanceof Element) {
//                treeWalk((Element) node);
//            } else {
//                
//            }
//        }
    }
    private String namespaceURI;
    private String nameValue;
    private String result;

    public boolean getResult() {
        if(null!=map.get("result")&&"success".equals(map.get("result").toString())){
           return true;
        }else {
            return false;
        }
    }

    
    
    /**
     * 获取uri前缀
     * @return 
     */
    public String getNamespaceURI(){
        return namespaceURI;
    }
    /**
     * 获取uri前缀
     * @return 
     */
    public String getFailMsg(){
        return map.get("message")==null?null:map.get("message").toString();
    }

    /**
     * 获取operate 节点的name值
     * @return 
     */
    public String getNameValue() {
        return nameValue;
    }
    
}
