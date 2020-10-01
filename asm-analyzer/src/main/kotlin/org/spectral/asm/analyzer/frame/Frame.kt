/*
 *     Spectral Powered
 *     Copyright (C) 2020 Kyle Escobar
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.spectral.asm.analyzer.frame

/**
 * Represents a instruction frame during a method execution.
 *
 * @property opcode Int
 * @constructor
 */
open class Frame(val opcode: Int) {

    /**
     * The parent frames which contributed to this frames value.
     */
    val parents = mutableListOf<Frame>()

    /**
     * The frames this frame contributed values to.
     */
    val children = mutableListOf<Frame>()

    /**
     * Whether the frame is pushing a constant value to the stack.
     */
    open val isConstant: Boolean = calculateConstant()

    /**
     * Calculates whether this frame has a constant pushed value.
     *
     * @return Boolean
     */
    private fun calculateConstant(): Boolean {
        var ret = true
        parents.forEach { parent ->
            ret = ret and parent.isConstant
        }

        return ret
    }


}