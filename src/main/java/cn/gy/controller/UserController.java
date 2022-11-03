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

    public static String StringFilter(String str)  {
        String regEx="[^\\u4E00-\\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
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
