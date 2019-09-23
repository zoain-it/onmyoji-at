package com.ikrka;

import com.ikrka.common.Config;
import com.ikrka.run.ATStart;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        if (args.length % 2 != 0 && !"-h".equals(args[0])) {
            return;
        }

        Options options = new Options();

        // 添加选项 -l --lib
        options.addOption(Option.builder("l") // 选项名称
                .longOpt("lib") // 长选项名
                .hasArg() // 需要参数
                .argName("path") // 参数显示名称
                // 选项的描述、帮助信息
                .desc("").build());
        // 添加选项 -a --adb
        options.addOption(Option.builder("a") // 选项名称
                .longOpt("adb") // 长选项名
                .hasArg() // 需要参数
                .argName("path") // 参数显示名称
                // 选项的描述、帮助信息
                .desc("").build());
        // 添加选项 -t --template
        options.addOption(Option.builder("t") // 选项名称
                .longOpt("template") // 长选项名
                .hasArg() // 需要参数
                .argName("path") // 参数显示名称
                // 选项的描述、帮助信息
                .desc("").build());
        // 添加选项 -h --help
        options.addOption(Option.builder("h") // 选项名称
                .longOpt("help") // 长选项名
                // 选项的描述、帮助信息
                .desc("show this help message and exit program").build());

        // 解析器
        CommandLineParser parser = new DefaultParser();
        // 格式器
        HelpFormatter formatter = new HelpFormatter();

        try {
            // 解析结果
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                String formatstr = "onmyoji-at cli";
                formatter.printHelp(formatstr, "", options, "");
                return;
            }

            if (cmd.hasOption("l")) {
                Config.libPath[0] = cmd.getOptionValue("l");
            } else {
                Console.log("onmyoji-at: Not set lib");
                return;
            }

            if (cmd.hasOption("a")) {
                Config.adb[0] = cmd.getOptionValue("a");
            }

            if (cmd.hasOption("t")) {
                Config.tempPath[0] = FileUtil.normalize(cmd.getOptionValue("t"));
                Config.tempPath[0] = Config.tempPath[0].substring(Config.tempPath[0].length() - 1).equals("/")
                        ? Config.tempPath[0]
                        : Config.tempPath[0] + "/";
            } else {
                Console.log("onmyoji-at: Not set template");
                return;
            }

        } catch (ParseException e) {
            Console.log("com.ikrka.App (line : 63) -> ParseException : {}", e.getMessage());
        }

        ATStart.run();
    }

}
