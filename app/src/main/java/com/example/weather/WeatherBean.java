package com.example.weather;

import java.util.List;

public class WeatherBean {
    //{
//	"reason":"查询成功!",
//	"result":{
//		"city":"广州",
//		"realtime":{
//			"temperature":"28",
//			"humidity":"87",
//			"info":"阴",
//			"wid":"02",
//			"direct":"南风",
//			"power":"1级",
//			"aqi":"47"
//		},
//		"future":[
//			{
//				"date":"2021-05-25",
//				"temperature":"26\/34℃",
//				"weather":"雷阵雨转阵雨",
//				"wid":{
//					"day":"04",
//					"night":"03"
//				},
//				"direct":"持续无风向"
//			},
//			{
//				"date":"2021-05-26",
//				"temperature":"27\/34℃",
//				"weather":"阵雨转雷阵雨",
//				"wid":{
//					"day":"03",
//					"night":"04"
//				},
//				"direct":"南风转持续无风向"
//			},
//			{
//				"date":"2021-05-27",
//				"temperature":"27\/34℃",
//				"weather":"雷阵雨转中雨",
//				"wid":{
//					"day":"04",
//					"night":"08"
//				},
//				"direct":"南风转持续无风向"
//			},
//			{
//				"date":"2021-05-28",
//				"temperature":"27\/32℃",
//				"weather":"中雨转雷阵雨",
//				"wid":{
//					"day":"08",
//					"night":"04"
//				},
//				"direct":"南风转持续无风向"
//			},
//			{
//				"date":"2021-05-29",
//				"temperature":"27\/34℃",
//				"weather":"雷阵雨",
//				"wid":{
//					"day":"04",
//					"night":"04"
//				},
//				"direct":"西南风转持续无风向"
//			}
//		]
//	},
//	"error_code":0
//}

    //提示
    private String reason;
    //数据
    private ResultBean result;
    //状态码
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String city;
        private RealtimeBean realtime;
        private List<FutureBean> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class RealtimeBean {
            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public static class FutureBean {
            private String date;
            private String temperature;
            private String weather;
            private WidBean wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidBean getWid() {
                return wid;
            }

            public void setWid(WidBean wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public static class WidBean {
                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }
}
