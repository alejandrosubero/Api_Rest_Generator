import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeSimpleComponent } from './code-simple.component';

describe('CodeSimpleComponent', () => {
  let component: CodeSimpleComponent;
  let fixture: ComponentFixture<CodeSimpleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CodeSimpleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeSimpleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
