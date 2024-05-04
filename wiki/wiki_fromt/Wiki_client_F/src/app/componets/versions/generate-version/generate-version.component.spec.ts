import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateVersionComponent } from './generate-version.component';

describe('GenerateVersionComponent', () => {
  let component: GenerateVersionComponent;
  let fixture: ComponentFixture<GenerateVersionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenerateVersionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GenerateVersionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
