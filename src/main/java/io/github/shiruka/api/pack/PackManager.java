/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.pack;

import io.github.shiruka.api.entity.Player;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine resource pack managers
 */
public interface PackManager extends Closeable {

  /**
   * closes the registration of resource packs.
   */
  void closeRegistration();

  /**
   * get resource pack loader instance from the given path.
   *
   * @param path the path to get.
   *
   * @return resource pack loader.
   *
   * @throws IOException if an I/O error has occurred.
   */
  @NotNull
  Optional<PackLoader> getLoader(@NotNull Path path) throws IOException;

  /**
   * gets resource pack manifest instance from the given loader.
   *
   * @param loader the loader to get.
   *
   * @return resource pack manifest.
   */
  @NotNull
  Optional<PackManifest> getManifest(@NotNull PackLoader loader);

  /**
   * loads pack from the given path.
   *
   * @param path the path to load.
   *
   * @throws IllegalStateException if no suitable loader found, if manifest not found, if the specified
   *   {@link PackManifest.PackType} is no supported.
   */
  void loadPack(@NotNull Path path);

  /**
   * loads packs from the given directory.
   *
   * @param directory the directory to load.
   */
  void loadPacks(@NotNull Path directory);

  /**
   * registers the given pack loader class.
   *
   * @param cls the class to register.
   * @param factory the factory to register.
   *
   * @throws IllegalArgumentException if the given cls is already registered.
   */
  void registerLoader(@NotNull Class<? extends PackLoader> cls, @NotNull PackLoader.Factory factory);

  /**
   * registers the given pack class.
   *
   * @param type the type to register.
   * @param factory the factory to register.
   *
   * @throws IllegalArgumentException if the given cls is already registered.
   */
  void registerPack(@NotNull PackManifest.PackType type, @NotNull Pack.Factory factory);

  /**
   * sends the pack infos packet to the given player.
   *
   * @param player the player to send.
   */
  void sendPackInfos(@NotNull Player player);
}
