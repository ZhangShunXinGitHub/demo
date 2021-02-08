package com.dean.dean.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * IP地址工具类
 */
@Slf4j
public class IpAddressUtil {

    /**
     * 获取本机IPV4
     * @return
     */
    public static List<String> localIPV4s(){
        List<String> list = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networks =  NetworkInterface.getNetworkInterfaces();
            while(networks.hasMoreElements()){
                NetworkInterface network = networks.nextElement();
                Enumeration<InetAddress> inetAddresses =  network.getInetAddresses();
                while (inetAddresses.hasMoreElements()){
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if(inetAddress instanceof Inet4Address){
                        list.add(inetAddress.getHostAddress());
                    }
                }
            }
            log.info("获取IP：{}",list);
        }catch (Exception e){
            log.warn("获取IP地址失败");
        }
        return list;
    }

    /**
     * 判断是否为本机IP
     * @param ipAddress
     * @return
     */
    public static boolean isLocalIp(String ipAddress){
        return localIPV4s().contains(ipAddress);
    }

}
