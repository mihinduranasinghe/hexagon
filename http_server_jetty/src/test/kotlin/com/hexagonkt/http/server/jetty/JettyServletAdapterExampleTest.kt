package com.hexagonkt.http.server.jetty

import com.hexagonkt.http.server.examples.*
import org.testng.annotations.Test

val adapter = JettyServletAdapter()
val asyncAdapter = JettyServletAdapter(true)

@Test class JettyServletAdapterBooksTest : BooksTest(adapter)
@Test class JettyServletAdapterCookiesTest : CookiesTest(adapter)
@Test class JettyServletAdapterSessionTest : SessionTest(adapter)
@Test class JettyServletAdapterErrorsTest : ErrorsTest(adapter)
@Test class JettyServletAdapterGenericTest : GenericTest(adapter)

@Test class JettyServletAdapterAsyncBooksTest : BooksTest(asyncAdapter)
@Test class JettyServletAdapterAsyncCookiesTest : CookiesTest(asyncAdapter)
@Test class JettyServletAdapterAsyncSessionTest : SessionTest(asyncAdapter)
@Test class JettyServletAdapterAsyncErrorsTest : ErrorsTest(asyncAdapter)
@Test class JettyServletAdapterAsyncGenericTest : GenericTest(asyncAdapter)
