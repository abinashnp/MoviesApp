package com.abinash.moviesapp.di.network


//class InterceptorChanging @Inject constructor(
//                                              )
////{
//   : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//       val original = chain.request()
////        val originalHttpUrl = original.url
//       val encodedPath = original.url.encodedPath
//       val query = original.url.query
//
//       val updatedHttpUrl = HttpUrl.Builder()
//           .scheme(original.url.scheme)
//           .host(httpsHost)
//           .encodedPath(encodedPath)
//           .query(query)
//           .build()
//
//       val requestBuilder = original.newBuilder()
// //          .header("Accept", "application/json")
//           .method(original.method, original.body)
//           .url(updatedHttpUrl)
//       // Request customization: add request headers
//       val request = requestBuilder.build()
//       return chain.proceed(request)
//  }


//}