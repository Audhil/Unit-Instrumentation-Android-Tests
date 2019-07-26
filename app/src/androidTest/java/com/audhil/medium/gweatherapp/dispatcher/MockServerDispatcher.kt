package com.audhil.medium.gweatherapp.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {

    //  valid response dispatcher
    class ResponseDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path.equals(
                    "/v1/forecast.json?key=41c23902be8e47c0a1d171804190206&q=13.0827,80.2707&days=5",
                    true
                )
            ) return MockResponse().setResponseCode(200).setBody(
                "{\n" +
                        "  \"current\" : {\n" +
                        "    \"gust_mph\" : 13.6,\n" +
                        "    \"gust_kph\" : 22,\n" +
                        "    \"precip_in\" : 0,\n" +
                        "    \"precip_mm\" : 0,\n" +
                        "    \"cloud\" : 50,\n" +
                        "    \"last_updated_epoch\" : 1559562308,\n" +
                        "    \"feelslike_f\" : 103.8,\n" +
                        "    \"feelslike_c\" : 39.899999999999999,\n" +
                        "    \"is_day\" : 1,\n" +
                        "    \"vis_miles\" : 4,\n" +
                        "    \"pressure_in\" : 30.100000000000001,\n" +
                        "    \"vis_km\" : 7,\n" +
                        "    \"wind_dir\" : \"SE\",\n" +
                        "    \"wind_degree\" : 130,\n" +
                        "    \"temp_f\" : 93.200000000000003,\n" +
                        "    \"humidity\" : 53,\n" +
                        "    \"wind_mph\" : 12.5,\n" +
                        "    \"condition\" : {\n" +
                        "      \"code\" : 1003,\n" +
                        "      \"text\" : \"Partly cloudy\",\n" +
                        "      \"icon\" : \"\\/\\/cdn.apixu.com\\/weather\\/64x64\\/day\\/116.png\"\n" +
                        "    },\n" +
                        "    \"pressure_mb\" : 1003,\n" +
                        "    \"wind_kph\" : 20.199999999999999,\n" +
                        "    \"last_updated\" : \"2019-06-03 17:15\",\n" +
                        "    \"temp_c\" : 34,\n" +
                        "    \"uv\" : 8\n" +
                        "  },\n" +
                        "  \"location\" : {\n" +
                        "    \"region\" : \"Tamil Nadu\",\n" +
                        "    \"country\" : \"India\",\n" +
                        "    \"localtime\" : \"2019-06-03 17:26\",\n" +
                        "    \"lon\" : 80.269999999999996,\n" +
                        "    \"lat\" : 13.08,\n" +
                        "    \"tz_id\" : \"Asia\\/Kolkata\",\n" +
                        "    \"name\" : \"Madras Vepery\",\n" +
                        "    \"localtime_epoch\" : 1559562999\n" +
                        "  },\n" +
                        "  \"forecast\" : {\n" +
                        "    \"forecastday\" : [\n" +
                        "      {\n" +
                        "        \"astro\" : {\n" +
                        "          \"sunset\" : \"06:32 PM\",\n" +
                        "          \"moonrise\" : \"05:30 AM\",\n" +
                        "          \"moonset\" : \"06:33 PM\",\n" +
                        "          \"sunrise\" : \"05:42 AM\"\n" +
                        "        },\n" +
                        "        \"day\" : {\n" +
                        "          \"totalprecip_mm\" : 3.7000000000000002,\n" +
                        "          \"maxwind_kph\" : 27.699999999999999,\n" +
                        "          \"totalprecip_in\" : 0.14999999999999999,\n" +
                        "          \"condition\" : {\n" +
                        "            \"code\" : 1063,\n" +
                        "            \"text\" : \"Patchy rain possible\",\n" +
                        "            \"icon\" : \"\\/\\/cdn.apixu.com\\/weather\\/64x64\\/day\\/176.png\"\n" +
                        "          },\n" +
                        "          \"mintemp_f\" : 88.700000000000003,\n" +
                        "          \"avgtemp_f\" : 92.700000000000003,\n" +
                        "          \"mintemp_c\" : 31.5,\n" +
                        "          \"avgtemp_c\" : 33.700000000000003,\n" +
                        "          \"avgvis_miles\" : 10,\n" +
                        "          \"uv\" : 11.199999999999999,\n" +
                        "          \"maxtemp_f\" : 98.799999999999997,\n" +
                        "          \"avghumidity\" : 56,\n" +
                        "          \"maxtemp_c\" : 37.100000000000001,\n" +
                        "          \"avgvis_km\" : 17.100000000000001,\n" +
                        "          \"maxwind_mph\" : 17.199999999999999\n" +
                        "        },\n" +
                        "        \"date_epoch\" : 1559520000,\n" +
                        "        \"date\" : \"2019-06-03\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"astro\" : {\n" +
                        "          \"sunset\" : \"06:33 PM\",\n" +
                        "          \"moonrise\" : \"06:22 AM\",\n" +
                        "          \"moonset\" : \"07:33 PM\",\n" +
                        "          \"sunrise\" : \"05:42 AM\"\n" +
                        "        },\n" +
                        "        \"day\" : {\n" +
                        "          \"totalprecip_mm\" : 0,\n" +
                        "          \"maxwind_kph\" : 29.199999999999999,\n" +
                        "          \"totalprecip_in\" : 0,\n" +
                        "          \"condition\" : {\n" +
                        "            \"code\" : 1003,\n" +
                        "            \"text\" : \"Partly cloudy\",\n" +
                        "            \"icon\" : \"\\/\\/cdn.apixu.com\\/weather\\/64x64\\/day\\/116.png\"\n" +
                        "          },\n" +
                        "          \"mintemp_f\" : 88.299999999999997,\n" +
                        "          \"avgtemp_f\" : 93.400000000000006,\n" +
                        "          \"mintemp_c\" : 31.300000000000001,\n" +
                        "          \"avgtemp_c\" : 34.100000000000001,\n" +
                        "          \"avgvis_miles\" : 12,\n" +
                        "          \"uv\" : 11.9,\n" +
                        "          \"maxtemp_f\" : 99.299999999999997,\n" +
                        "          \"avghumidity\" : 54,\n" +
                        "          \"maxtemp_c\" : 37.399999999999999,\n" +
                        "          \"avgvis_km\" : 19.899999999999999,\n" +
                        "          \"maxwind_mph\" : 18.100000000000001\n" +
                        "        },\n" +
                        "        \"date_epoch\" : 1559606400,\n" +
                        "        \"date\" : \"2019-06-04\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"astro\" : {\n" +
                        "          \"sunset\" : \"06:33 PM\",\n" +
                        "          \"moonrise\" : \"07:19 AM\",\n" +
                        "          \"moonset\" : \"08:33 PM\",\n" +
                        "          \"sunrise\" : \"05:42 AM\"\n" +
                        "        },\n" +
                        "        \"day\" : {\n" +
                        "          \"totalprecip_mm\" : 0,\n" +
                        "          \"maxwind_kph\" : 33.799999999999997,\n" +
                        "          \"totalprecip_in\" : 0,\n" +
                        "          \"condition\" : {\n" +
                        "            \"code\" : 1003,\n" +
                        "            \"text\" : \"Partly cloudy\",\n" +
                        "            \"icon\" : \"\\/\\/cdn.apixu.com\\/weather\\/64x64\\/day\\/116.png\"\n" +
                        "          },\n" +
                        "          \"mintemp_f\" : 87.599999999999994,\n" +
                        "          \"avgtemp_f\" : 92.099999999999994,\n" +
                        "          \"mintemp_c\" : 30.899999999999999,\n" +
                        "          \"avgtemp_c\" : 33.399999999999999,\n" +
                        "          \"avgvis_miles\" : 12,\n" +
                        "          \"uv\" : 11.6,\n" +
                        "          \"maxtemp_f\" : 98.400000000000006,\n" +
                        "          \"avghumidity\" : 57,\n" +
                        "          \"maxtemp_c\" : 36.899999999999999,\n" +
                        "          \"avgvis_km\" : 19.899999999999999,\n" +
                        "          \"maxwind_mph\" : 21\n" +
                        "        },\n" +
                        "        \"date_epoch\" : 1559692800,\n" +
                        "        \"date\" : \"2019-06-05\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"astro\" : {\n" +
                        "          \"sunset\" : \"06:33 PM\",\n" +
                        "          \"moonrise\" : \"08:18 AM\",\n" +
                        "          \"moonset\" : \"09:32 PM\",\n" +
                        "          \"sunrise\" : \"05:42 AM\"\n" +
                        "        },\n" +
                        "        \"day\" : {\n" +
                        "          \"totalprecip_mm\" : 0,\n" +
                        "          \"maxwind_kph\" : 32.799999999999997,\n" +
                        "          \"totalprecip_in\" : 0,\n" +
                        "          \"condition\" : {\n" +
                        "            \"code\" : 1003,\n" +
                        "            \"text\" : \"Partly cloudy\",\n" +
                        "            \"icon\" : \"\\/\\/cdn.apixu.com\\/weather\\/64x64\\/day\\/116.png\"\n" +
                        "          },\n" +
                        "          \"mintemp_f\" : 86.900000000000006,\n" +
                        "          \"avgtemp_f\" : 92.299999999999997,\n" +
                        "          \"mintemp_c\" : 30.5,\n" +
                        "          \"avgtemp_c\" : 33.5,\n" +
                        "          \"avgvis_miles\" : 12,\n" +
                        "          \"uv\" : 12,\n" +
                        "          \"maxtemp_f\" : 99.700000000000003,\n" +
                        "          \"avghumidity\" : 60,\n" +
                        "          \"maxtemp_c\" : 37.600000000000001,\n" +
                        "          \"avgvis_km\" : 19.899999999999999,\n" +
                        "          \"maxwind_mph\" : 20.399999999999999\n" +
                        "        },\n" +
                        "        \"date_epoch\" : 1559779200,\n" +
                        "        \"date\" : \"2019-06-06\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"astro\" : {\n" +
                        "          \"sunset\" : \"06:34 PM\",\n" +
                        "          \"moonrise\" : \"09:20 AM\",\n" +
                        "          \"moonset\" : \"10:27 PM\",\n" +
                        "          \"sunrise\" : \"05:42 AM\"\n" +
                        "        },\n" +
                        "        \"day\" : {\n" +
                        "          \"totalprecip_mm\" : 11.1,\n" +
                        "          \"maxwind_kph\" : 29.899999999999999,\n" +
                        "          \"totalprecip_in\" : 0.44,\n" +
                        "          \"condition\" : {\n" +
                        "            \"code\" : 1243,\n" +
                        "            \"text\" : \"Moderate or heavy rain shower\",\n" +
                        "            \"icon\" : \"\\/\\/cdn.apixu.com\\/weather\\/64x64\\/day\\/356.png\"\n" +
                        "          },\n" +
                        "          \"mintemp_f\" : 86.200000000000003,\n" +
                        "          \"avgtemp_f\" : 90.900000000000006,\n" +
                        "          \"mintemp_c\" : 30.100000000000001,\n" +
                        "          \"avgtemp_c\" : 32.700000000000003,\n" +
                        "          \"avgvis_miles\" : 11,\n" +
                        "          \"uv\" : 12.4,\n" +
                        "          \"maxtemp_f\" : 96.799999999999997,\n" +
                        "          \"avghumidity\" : 60,\n" +
                        "          \"maxtemp_c\" : 36,\n" +
                        "          \"avgvis_km\" : 19.300000000000001,\n" +
                        "          \"maxwind_mph\" : 18.600000000000001\n" +
                        "        },\n" +
                        "        \"date_epoch\" : 1559865600,\n" +
                        "        \"date\" : \"2019-06-07\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"
            )
            return MockResponse().setResponseCode(400)
        }
    }

    //  error dispatcher
    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse = MockResponse().setResponseCode(400)
    }
}