package com.lorent.web.xmlrpc.handler;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.tree.OrgNode;
import com.lorent.common.tree.OrgTree;


public class OrganHandler extends BaseHandler{
	
	public OrgTree<DepartmentBean, MemberBean> getOrganTree(){
		Integer customerId = serviceFacade.getCustomerService().getFirstValidCustomer().getId();
		return serviceFacade.getDepartmentService().getOrgTreeByCustomer(customerId);
	}
	
	public static void main(String[] args)throws Exception {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://127.0.0.1:6090/lcm/lcmRpc"));
		config.setEnabledForExtensions(true);
		config.setEnabledForExceptions(true);
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		long before = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		OrgTree ret = (OrgTree)client.execute("lcmOrgan.getOrganTree", new Object[]{});
		long after = System.currentTimeMillis();
		long bet = after - before;
		System.out.println("time : " + bet);
		printTree(ret.getRoot(), "");
	}
	
	private static void printTree(OrgNode<DepartmentBean, MemberBean> node, String prefix){
		if(node.getMember()!=null){
			System.out.println(prefix + "(M)" + node.getMember().getUsername());
		}else if(node.getOrganization()!=null){
			System.out.println(prefix + "(O)" + node.getOrganization().getName());
			if(node.getChildNodes().size() > 0){
				prefix = prefix + "\t";
				for(OrgNode<DepartmentBean, MemberBean> org : node.getChildNodes()){
					printTree(org, prefix);
				}

			}
		}
		
	}
}
