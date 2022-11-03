package cn.gy.controller;

import cn.gy.model.Item;
import cn.gy.model.User;
import cn.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.gy.controller.RandomCity.randomDate;


@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = userService.getUserInfo();
        if(user!=null){
            System.out.println("user.getName():"+user.getSn());
        }
        return user;
    }

    @RequestMapping("/addUserInfo")
    @ResponseBody
    public int addUserInfo() {
        List<User> userList = new ArrayList<>();

       //readCSV("E:\\\\BaiduNetdiskDownload\\\\豆瓣电影数据集\\users.csv", userList);
        int user = userService.addUserInfo(userList);
        System.out.println("添加完成:"+user);
        return user;
    }


    @RequestMapping("/addItemInfo")
    @ResponseBody
    public int addItemInfo() {
        List<Item> userList = new ArrayList<>();
       // readCSV2("E:\\BaiduNetdiskDownload\\豆瓣电影数据集\\movies.csv", userList);
        int user = userService.addItemInfo(userList);
        System.out.println("添加完成:"+user);
        return 0;
    }



    @RequestMapping("/createAction")
    @ResponseBody
    public int createAction() {
        readCSV3("E:\\BaiduNetdiskDownload\\豆瓣电影数据集\\movies.csv");
        System.out.println("添加完成");
        return 0;
    }

    public static String StringFilter(String str)  {
        String regEx="[^\\u4E00-\\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }



    public static void readCSV3(String readpath)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates = null;
        try {
            dates = randomDate("1950-6-21 00:00:00", "2022-12-01 23:59:59", 900000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dates.forEach(t -> System.out.println(sdf.format(t)));

        File inFile = new File(readpath);
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            boolean sign = false;       //用来跳过第一行的名称
            while(reader.ready())
            {
                String line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                String sn,item,duration,time,area,contentModel, desc;

                if (st.hasMoreTokens() && sign)
                {
                    st.nextToken().trim().replace("\"", "");
                    sn = st.nextToken().trim().replace("\"", "");
                    item = st.nextToken().trim().replace("\"", "");
                    duration = st.nextToken().trim().replace("\"", "");
                    time = st.nextToken().trim().replace("\"", "");

                }
                else
                {
                    sign = true;
                }
            }
            reader.close();

        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
    }




    public void writeFile(String[] arrs, String path) throws IOException {
/*        String[] arrs={
                "zhangsan,23,福建",
                "lisi,30,上海",
                "wangwu,43,北京",
                "laolin,21,重庆",
                "ximenqing,67,贵州"

                "E:/phsftp/evdokey/evdokey_201103221556.txt"
        };*/
        //写入中文字符时解决中文乱码问题
        FileOutputStream fos=new FileOutputStream(new File(path));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //简写如下：
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for(String arr:arrs){
            bw.write(arr+"\t\n");
        }

        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
        bw.close();
        osw.close();
        fos.close();
    }


    public static void readCSV2(String readpath, List<Item> list)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates = null;
        try {
            dates = randomDate("1950-6-21 00:00:00", "2022-12-01 23:59:59", 900000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dates.forEach(t -> System.out.println(sdf.format(t)));

        File inFile = new File(readpath);
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            boolean sign = false;       //用来跳过第一行的名称
            while(reader.ready())
            {
                String line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                String name,title,focus,language,area,contentModel, desc;
                Long id;

                if (st.hasMoreTokens() && sign)
                {
                    Item user = new Item();
                    id = Long.valueOf(st.nextToken().trim().replace("\"", ""));
                    name = st.nextToken().trim().replace("\"", "");
                    title = st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    focus = st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    language = st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    area = st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    st.nextToken().trim().replace("\"", "");
                    contentModel = StringFilter(st.nextToken().trim().replace("\"", ""));
                    desc = st.nextToken().trim().replace("\"", "");

                    user.setId(id);
                    user.setName(name);
                    user.setTitle(title);
                    user.setFocus(focus);
                    user.setKeywords(focus);
                    user.setLanguage(language);
                    user.setArea(area);
                    user.setContentModel(contentModel);
                    user.setDesc(desc);
                    user.setCreateDate(dates.get((int) (Math.random()* dates.size())));
                    user.setAirDate(dates.get((int) (Math.random()* dates.size())));
                    user.setLastMod(new Date());
                    user.setLength(new Random().nextInt(60)+120);
                    user.setQuality("2");
                    user.setIs_3d(String.valueOf(new Random().nextBoolean()));
                    list.add(user);
                }
                else
                {
                    sign = true;
                }
            }
            reader.close();

        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
    }


}
