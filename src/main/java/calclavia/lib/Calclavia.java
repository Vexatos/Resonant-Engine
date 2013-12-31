package calclavia.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import calclavia.components.CalclaviaCore;
import cpw.mods.fml.common.Loader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Configuration;

public class Calclavia
{
	public static final String NAME = "Calclavia";

	public static final String DOMAIN = "calclavia";
	public static final String TEXTURE_NAME_PREFIX = DOMAIN + ":";

	public static final String RESOURCE_DIRECTORY = "/assets/calclavia/";

	public static final String TEXTURE_DIRECTORY = "textures/";
	public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "gui/";

	public static final ResourceLocation GUI_EMPTY_FILE = new ResourceLocation(DOMAIN, GUI_DIRECTORY + "gui_empty.png");
	public static final ResourceLocation GUI_COMPONENTS = new ResourceLocation(DOMAIN, GUI_DIRECTORY + "gui_components.png");
	public static final ResourceLocation GUI_BASE = new ResourceLocation(DOMAIN, GUI_DIRECTORY + "gui_base.png");
	/**
	 * The configuration file.
	 */
	public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(), CalclaviaCore.NAME + ".cfg"));

	public static List<String> splitStringPerWord(String string, int wordsPerLine)
	{
		String[] words = string.split(" ");
		List<String> lines = new ArrayList<String>();

		for (int lineCount = 0; lineCount < Math.ceil((float) words.length / (float) wordsPerLine); lineCount++)
		{
			String stringInLine = "";

			for (int i = lineCount * wordsPerLine; i < Math.min(wordsPerLine + lineCount * wordsPerLine, words.length); i++)
			{
				stringInLine += words[i] + " ";
			}

			lines.add(stringInLine.trim());
		}

		return lines;
	}

}
