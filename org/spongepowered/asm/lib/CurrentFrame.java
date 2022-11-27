package org.spongepowered.asm.lib;

class CurrentFrame extends Frame {
  void execute(int paramInt1, int paramInt2, ClassWriter paramClassWriter, Item paramItem) {
    super.execute(paramInt1, paramInt2, paramClassWriter, paramItem);
    Frame frame = new Frame();
    merge(paramClassWriter, frame, 0);
    set(frame);
    this.owner.inputStackTop = 0;
  }
}
