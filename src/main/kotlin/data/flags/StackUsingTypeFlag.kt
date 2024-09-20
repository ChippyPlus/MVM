package data.flags

import data.flags.enumIdenifiers.StackUsingTypeType
import errors

class StackUsingTypeFlag {
    private var internalFlag: StackUsingTypeType? = null


    fun write(flag: StackUsingTypeType) {
        internalFlag = flag
    }

    fun read(): StackUsingTypeType? {
        return internalFlag
    }

    fun toggle() {
        if (internalFlag == null) {
            errors.NullFlagException("StackUsingTypeFlag")
        }

        when (internalFlag!!) {
            StackUsingTypeType.Long -> internalFlag.run {
                this@StackUsingTypeFlag.write(StackUsingTypeType.Double)
            }

            StackUsingTypeType.Double -> internalFlag.run {
                this@StackUsingTypeFlag.write(StackUsingTypeType.Double)

            }
        }
    }
}
