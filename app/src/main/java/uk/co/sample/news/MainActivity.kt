package uk.co.sample.news

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    external fun MainEntry()

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        System.loadLibrary("c++_shared") // (if we didn't compile statically)

        // Notice that I renamed the ICU libraries & symbols
        System.loadLibrary("icudataswift")
        System.loadLibrary("icuucswift")
        System.loadLibrary("icui18nswift")

        System.loadLibrary("swiftSwiftOnoneSupport") // used by swiftCore
        System.loadLibrary("swiftCore")

        System.loadLibrary("swiftGlibc") // Should be named Bionic

        // System.loadLibrary("dispatch");
        // System.loadLibrary(“Foundation"); // (may have additional dependencies)
        // This is our Swift code compiled into a dynamic library
        System.loadLibrary("MySwiftMainProgram")
    }
}